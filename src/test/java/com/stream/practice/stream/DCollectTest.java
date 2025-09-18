package com.stream.practice.stream;

import static java.util.Comparator.comparingInt;

import com.stream.practice.basic.Apple;
import com.stream.practice.basic.AppleBox;
import com.stream.practice.basic.Color;
import com.stream.practice.basic.SugarGrade;
import java.util.Arrays;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * collect()는 수집기이다.(최종 연산자)
 * 여러가지 집계, 여러 자료구조로 변환 등이 가능하다.
 * .toList vs .collect(Collectors.toList())
 * => toList는 UnModifiedList 즉, add() remove() 등을 할 수 없는 불변 리스트(자바 16이후에만 사용가능)
 * => collect(Collectors.toList())는 가변 리스트
 * Collectors라는 클래스에는 여러 집계 메서드들이 존재한다.
 * collect()를 사용하여 여러가지 형태로 집계해보고, 여러 자료구조로 바꿔본다.
 * => 사실, Collectors에는 너무도 많은 메서드가 존재하고, 이걸 다 아는 것은 불가능(??어렵기)하기 때문에, 이런게 있구나~하면서 넘어가면
 * 될 듯
 * => 대부분은 쿼리로 해결이 가능한 것들이기도 하다.
 * => 1,2번만 알고 넘어가도 될 듯하다.(나머지는 GPT를 적극 활용하여, 감만 잡으면 될듯하다.)
 */
@SpringBootTest
public class DCollectTest {

    @Autowired
    private AppleBox appleBox;

    @DisplayName("""
            <<collect() 기초>>
            collect()를 사용하여, apples를 불변 리스트로 반환하여라.
            """)
    @Test
    public void collectAndGroupBy() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        List<Apple> unmodifiableApples = null;

        //then
        Assertions.assertThat(unmodifiableApples)
                .hasSize(100)
                .containsExactlyElementsOf(apples);

        Assertions.assertThatThrownBy(() -> unmodifiableApples.add(Apple.of(100, Color.RED, SugarGrade.LOW, 1000)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("""
            <<가격별 그루핑>>
            사과 상자에는 100개의 사과가 있다.
            해당 사과를 쉽게 팔기 위해, price로 묶어 Map의 자료구조로 바꾸려고 한다.
            이때, collect()를 사용하면 쉽게 된다는 것을 알았다.
            collect(Collectors.groupingBy())를 사용하여, List<Apple>을 Map<Integer, List<Apple>>의 형태로 바꿔본다.
            (가격 1200원인 것들에는 이러이러한 사과들이 있다!)
            
            => Collectors의 메서드들은 Collector를 반환한다.
            => Collector안에는 여러 메서드들이 있다.
            => 그 중 groupingBy()는 key로 모아, Map<key, List<>>의 형태로 반환해주는 역할을 한다.
            => 쉽게 Map으로 변환해줄 수 있어 사용할 수 있겠다.
            => ex) List<User>를 들고와서 Map<Pk, User>로 매핑 후 사용
            """)
    @Test
    public void collectAndGroupBy1() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        Map<Integer, List<Apple>> applesMap = null;

        //then
        applesMap.forEach((price, list) ->
            Assertions.assertThat(list)
                .allSatisfy(a -> Assertions.assertThat(a.getPrice()).isEqualTo(price)));
    }

    @DisplayName("""
            <<색상 별 갯수>>
            사과 상자에는 100개의 사과가 있다.
            해당 사과 상자의 색상 별로 분류했을 때, 몇개씩 있는지 알고 싶다.
            Collectors.groupingBy()와 counting()을 이용하여 Map<Color, Long>으로 반환하여라.
            """)
    @Test
    public void collectAndGroupBy2() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        Map<Color, Long> appleMap = null;

        //then
        Map<Color, Long> expected = Arrays.stream(Color.values())
            .collect(Collectors.toMap(Function.identity(),
                c -> apples.stream().filter(a -> a.getColor() == c).count()));
        Assertions.assertThat(appleMap).isEqualTo(expected);
    }

    @DisplayName("""
            <<색상 별 평균 가격>>
            사과 상자에는 100개의 사과가 있다.
            해당 사과 상자의 색상 별로 분류했을 때, 평균적으로 얼마인지 알고 싶다.
            Collectors.groupingBy()와 averaging()을 이용하여 Map<Color, Double>으로 반환하여라.
            """)
    @Test
    public void collectAndGroupBy3() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        Map<Color, Double> applesMap = null;

        //then
        Map<Color, Double> expected = Arrays.stream(Color.values())
            .collect(Collectors.toMap(Function.identity(), c -> {
                var stats = apples.stream()
                    .filter(a -> a.getColor() == c)
                    .mapToInt(Apple::getPrice)
                    .summaryStatistics();
                return stats.getCount() == 0 ? 0d : stats.getAverage();
            }));

        expected.forEach((color, avg) ->
            Assertions.assertThat(applesMap.getOrDefault(color, 0d))
                .isCloseTo(avg, Assertions.offset(1e-9)));
    }

    @DisplayName("""
            <<색상 별 최고가>>
            사과 상자에는 100개의 사과가 있다.
            해당 사과 상자의 색상 별로 분류했을 때, 평균적으로 얼마인지 알고 싶다.
            Collectors.groupingBy()와 max()을 이용하여 Map<Color, Double>으로 반환하여라.
            """)
    @Test
    public void collectAndGroupBy4() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        Map<Color, Optional<Apple>> applesMap = null;

        //then
        Assertions.assertThat(applesMap.keySet())
            .containsExactlyInAnyOrder(Color.values());

        Arrays.stream(Color.values()).forEach(color -> {
            Optional<Apple> fromMap = applesMap.get(color);
            Assertions.assertThat(fromMap).isPresent();

            Optional<Apple> expected = apples.stream()
                .filter(a -> a.getColor() == color)
                .max(comparingInt(Apple::getPrice));

            Assertions.assertThat(fromMap).isEqualTo(expected);
        });

        applesMap.values().forEach(opt ->
            opt.ifPresent(a ->
                Assertions.assertThat(a.getPrice()).isBetween(1000, 2000)));
    }

    @DisplayName("""
            <<특정 조건으로 그룹 나누기>>
            가격이 1500 초과 여부로 그룹을 나눠 Map<Boolean, List<Apple>>로 만들어라.
            """)
    @Test
    public void collect6() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        Map<Boolean, List<Apple>> applesMap = null;

        //then
        List<Apple> gt = applesMap.get(true);
        List<Apple> le = applesMap.get(false);

        Assertions.assertThat(gt).doesNotContainAnyElementsOf(le);
        Assertions.assertThat(gt.size() + le.size()).isEqualTo(apples.size());

        Assertions.assertThat(gt).allSatisfy(a ->
            Assertions.assertThat(a.getPrice()).isGreaterThan(1500));
        Assertions.assertThat(le).allSatisfy(a ->
            Assertions.assertThat(a.getPrice()).isLessThanOrEqualTo(1500));

        Assertions.assertThat(gt)
            .containsExactlyInAnyOrderElementsOf(
                apples.stream().filter(a -> a.getPrice() > 1500).toList());
        Assertions.assertThat(le)
            .containsExactlyInAnyOrderElementsOf(
                apples.stream().filter(a -> a.getPrice() <= 1500).toList());
    }
}


























/*
1번
            apples.stream()
                .collect(Collectors.toUnmodifiableList());

2번
            apples.stream()
                .collect(Collectors.groupingBy(Apple::getPrice));

3번
            apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()));

4번
            apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingInt(Apple::getPrice)));

5번
            apples.stream()
                .collect(Collectors.groupingBy(
                        Apple::getColor,
                        Collectors.maxBy((o1, o2) -> o1.getPrice() - o2.getPrice()))
                );

6번
            apples.stream()
                .collect(Collectors.partitioningBy(apple -> apple.getPrice() > 1500));

 */