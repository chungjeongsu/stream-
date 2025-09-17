package com.stream.practice.stream;

import com.stream.practice.basic.Apple;
import com.stream.practice.basic.AppleBox;
import com.stream.practice.basic.Color;
import com.stream.practice.basic.SugarGrade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 감을 끌어올린다.
 * filter -> 조건 걸러내기
 * map -> 변환하기
 * sorted -> 정렬하기
 * reduce -> 요소들을 소비(단계1)
 * collect -> 고급 소비(단계2)
 * 위의 5가지 연산을 자유자재로 사용할 수 있게끔 한다.
 */
@SpringBootTest
public class HMixedTest {

    @Autowired
    private AppleBox appleBox;

    @DisplayName("""
            <<조건2개 필터링 + 매핑>>
            => 색깔이 RED이고, 
            => 가격이 1300원 이상인 사과들의 
            => weight만 모아 List<Integer>로 만들어라    
            """)
    @Test
    public void mix1() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<Integer> weights = null;

        //then
        Assertions.assertThat(weights)
                .containsExactly(
                        133, 148, 113, 100, 149, 132, 107, 107,
                        135, 136, 105, 139, 147, 149, 132, 108
                );
    }

    @DisplayName("""
            <<필터링 + 정렬>>
            => 색깔이 GREEN인 사과들을
            => 가격 기준 내림차순으로 정렬하여라.
            """)
    @Test
    public void mix2() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<Apple> filteredSortedApples = null;

        //then
        Assertions.assertThat(filteredSortedApples)
                .allSatisfy(apple -> Assertions.assertThat(apple.getColor()).isEqualTo(Color.GREEN))
                .extracting(Apple::getPrice)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @DisplayName("""
            => 색깔이 GREEN인 사과들을
            => 가격 기준 내림차순으로 정렬하여라.
            """)
    @Test
    public void mix3() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)

        //then

    }

    @DisplayName("""
            <<필터링 + collect 또는 필터링 + reduce()>>
            => 당도가 HIGH인 사과들의
            => 총 가격을 구하라
            """)
    @Test
    public void mix4() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        int totalPrice = 0;

        //then
        Assertions.assertThat(totalPrice).isEqualTo(38500);
    }

    @DisplayName("""
            <<max(), Optional 반환>>
            => 가장 무거운 사과를 찾아라.
            => 만약, Optional로 반환하고, 없다면, NoSuchElementException을 반환하여라.
            
            **여러가지 리스트 중 하나를 찾아라!이다. 그렇다면, 이러한 조회와 같은 메서드는 null을 고려해야하는가?를 생각해볼 것.(중요)**
            => max() 메서드가 파라미터로 어떤 함수형 인터페이스를 받는지 확인해볼 것.
            => 해당 함수형 인터페이스는 어떤 것을 받고, 어떤 것을 반환하는지 확인해볼 것.
            => null이 나올 수 있는 메서드에서 자바진영에서 null 안전성을 높이기 위해 어떠한 wrapper를 사용했는지 살펴볼 것.
            """)
    @Test
    public void mix5() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        Apple heaviestApple = null;

        //then
        Assertions.assertThat(heaviestApple)
                .extracting(Apple::getWeight)
                .isEqualTo(150);
    }

    @DisplayName("""
            <<sorted + limit + mapToInt + sum>>
            => 가장 무거운 사과 3개를 골라
            => 합을 구하여라
            """)
    @Test
    public void mix6() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        int heaviestAppleSum = 0;

        //then
        Assertions.assertThat(heaviestAppleSum).isEqualTo(449);
    }

    @DisplayName("""
            <<filter + collect()>>
            => 가격이 1300원 이상인 사과들 중
            => 색깔 별 무게 평균을 내서
            => Map<Color, Double>의 형태로 반환하여라.
            """)
    @Test
    public void mix7() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when
        Map<Color, Double> applePriceAverageMap = null;

        //then
        Assertions.assertThat(applePriceAverageMap)
                .containsEntry(Color.RED, 1668.75)
                .containsEntry(Color.GREEN, 1600.0)
                .containsEntry(Color.WHITE, 1506.6666666666667)
                .containsEntry(Color.BLACK, 1529.1666666666667);
    }

    @DisplayName("""
            => 무게가 120 이상인 것들을 골라
            => 가격이 비싼 것들 순으로 정렬하여,
            => List<Color>의 형태로 반환하여라.
            """)
    @Test
    public void mix8() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when

        //then
    }
}














/*
1번
            apples.stream()
                .filter(apple -> apple.getColor() == Color.RED)
                .filter(apple -> apple.getPrice() >= 1300)
                .map(Apple::getWeight)
                .toList();

            apples.stream()
                .filter(apple -> apple.getColor() == Color.RED && apple.getPrice() >= 1300)
                .map(Apple::getWeight)
                .toList();

            apples.stream()
                .filter(apple -> apple.getColor() == Color.RED && apple.getPrice() >= 1300)
                .collect(Collectors.mapping(Apple::getWeight, Collectors.toList()));

2번
            apples.stream()
                .filter(apple -> apple.getColor() == Color.GREEN)
                .sorted((o1, o2) -> o2.getPrice() - o1.getPrice())
                .toList();

3번



4번
            apples.stream()
                .filter(apple -> apple.getSugarGrade() == SugarGrade.HIGH)
                .mapToInt(Apple::getPrice)
                .reduce(0, Integer::sum);

            apples.stream()
                .filter(apple -> apple.getSugarGrade() == SugarGrade.HIGH)
                .collect(Collectors.summingInt(Apple::getPrice));

            apples.stream()
                .filter(apple -> apple.getSugarGrade() == SugarGrade.HIGH)
                .mapToInt(Apple::getPrice)
                .sum();

5번
            apples.stream()
                .max(Comparator.comparing(Apple::getWeight())
                .orElseThrow(() -> new NoSuchElementException());

6번
            apples.stream()
                .sorted(Comparator.comparing(Apple::getWeight).reversed())
                .limit(3)
                .mapToInt(Apple::getWeight)
                .sum();

7번
            apples.stream()
                .filter(apple -> apple.getPrice() >= 1300)
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingInt(Apple::getPrice)));
 */