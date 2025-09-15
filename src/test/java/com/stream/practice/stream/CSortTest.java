package com.stream.practice.stream;

import com.stream.practice.basic.Apple;
import com.stream.practice.basic.AppleBox;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

/**
 * 모든 스트림의 연산은 최종 연산자 호출 시 위에서 아래로 실행이된다.
 * 하지만, sorted()나 distinct()와 같이 전체의 집합이 있어야 실행이 가능한 연산이 있다.
 * 이러한 놈들은 일반적인 연산자와 다르게 동작한다.
 * 일반적으로 버퍼에 모아 한번에 실행할 수 밖에 없는데, 이때문에, 일반적인 순서와는 조금은 다르다는 특징이 있다.
 *
 */
@SpringBootTest
public class CSortTest {

    @Autowired
    private AppleBox appleBox;

    @DisplayName("""
            <<sorted() 기본>>
            사과 상자에는 100개의 사과가 있다.
            이 사과 상자의 사과를 정렬한 List를 받고 싶다.
            하지만, Collections.sort()나 apples.sort()를 쓰기엔, 원본 값이 변경돼 버린다.
            젠장.
            stream API를 이용하여, 정렬된 사과 List를 받아보자.
            내림차순으로 정렬한다!
            가격순으로 정렬한다!
            
            => sorted() 메서드가 파라미터로 어떤 함수형 인터페이스를 받는지 확인해볼 것.
            => 해당 함수형 인터페이스는 어떤 것을 받고, 어떤 것을 반환하는지 확인해볼 것.
            => 만약, sorted({람다식})으로 구현했다면, 다른 어떤 구현 방법이 있는지 생각해볼 것.
            """)
    @Test
    public void sort1() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<Apple> sortedApples = null;

        //then
        Assertions.assertThat(sortedApples)
                .hasSize(100)
                .extracting(Apple::getPrice)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @DisplayName("""
            <<sorted() 조건 2개 정렬>>
            사과 상자에는 100개의 사과가 있다.
            이 사과 상자의 사과를 정렬한 List를 받고 싶다.
            하지만, Collections.sort()나 apples.sort()를 쓰기엔, 원본 값이 변경돼 버린다.
            젠장.
            stream API를 이용하여, 정렬된 사과 List를 받아보자.
            내림차순으로 정렬한다!
            가격순으로 정렬한다!(첫번째 기준)
            무게 순으로도 정렬한다!(두번째 기준)
            
            => 기준의 순서를 제대로 확인한다. Stream의 특성을 이해하고, sorted()가 어떤 방식으로 실행되는지 이해하고 풀어본다.
            (힌트 : Stream 파이프라인 실행 순서와 sorted() 버퍼)
            """)
    @Test
    public void sort2() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<Apple> sortedApples = null;

        //then
        Assertions.assertThat(sortedApples)
                .hasSize(100)
                .isSortedAccordingTo(
                        Comparator.comparing(Apple::getPrice).reversed()
                                .thenComparing(Comparator.comparingInt(Apple::getWeight).reversed())
                );
    }

    @DisplayName("""
            <<sorted() 조건 2개 정렬(함수 합성)>>
            사과 상자에는 100개의 사과가 있다.
            이 사과 상자의 사과를 정렬한 List를 받고 싶다.
            하지만, Collections.sort()나 apples.sort()를 쓰기엔, 원본 값이 변경돼 버린다.
            젠장.
            stream API를 이용하여, 정렬된 사과 List를 받아보자.
            내림차순으로 정렬한다!
            가격순으로 정렬한다!(첫번째 기준)
            무게 순으로도 정렬한다!(두번째 기준)
            
            => 함수 합성을 이용해본다.
            => 함수 합성 vs 2번의 정답과 정렬 조건 적용 순서를 제대로 이해한다.
            """)
    @Test
    public void sort3() {
        //given
        List<Apple> apples = appleBox.getApples();

        Comparator<Apple> priceDesc  = null;
        Comparator<Apple> weightDesc = null;

        //when(정답코드 작성)
        List<Apple> sortedApples = null;

        //then
        Assertions.assertThat(sortedApples)
                .hasSize(100)
                .isSortedAccordingTo(
                        Comparator.comparing(Apple::getPrice).reversed()
                                .thenComparing(Comparator.comparingInt(Apple::getWeight).reversed())
                );
    }
}

























/*
1번
            apples.stream()
                .sorted(((o1, o2) -> o2.getPrice() - o1.getPrice()))
                .toList();

            apples.stream()
                .sorted(Comparator.comparing(Apple::getPrice).reversed())
                .toList();

            ++comparable 구현

2번
            apples.stream()
                .sorted((o1, o2) -> o2.getWeight() - o1.getWeight())
                .sorted(((o1, o2) -> o2.getPrice() - o1.getPrice()))
                .toList();

3번
            Comparator<Apple> priceDesc  = Comparator.comparing(Apple::getPrice).reversed();
            Comparator<Apple> weightDesc = Comparator.comparing(Apple::getWeight).reversed();

            Comparator<Apple> priceDesc  = (o1, o2) -> o2.getPrice() - o1.getPrice();
            Comparator<Apple> weightDesc = (o1, o2) -> o2.getWeight() - o1.getWeight();

            apples.stream()
                .sorted(priceDesc.thenComparing(weightDesc))
                .toList();
 */