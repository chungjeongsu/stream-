package com.stream.practice.stream;

import com.stream.practice.basic.Apple;
import com.stream.practice.basic.AppleBox;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * reduce()는 모든 요소를 소비하는 것이다.
 * => 소비하여 모두 더할 수 있다.
 * => 소비하여 비교함으로 max값을 구할 수 있다.
 */
@SpringBootTest
public class EReduceTest {

    @Autowired
    private AppleBox appleBox;

    @DisplayName("""
            <<reduce() 기본>>
            사과 상자에는 100개의 사과가 있다.
            이 사과들을 전부 팔았을 때, 총 얼마가 나오는지 알고 싶다!
            모든 사과들의 총합을 reduce()를 사용하여 구하여라!
            mapToInt(Apple::getPrice)를 사용하여 사과의 가격으로 매핑 후 reduce를 사용하여라.
            => 초깃값을 0으로 두고 연산하여라!
            
            => T reduce(T identity, BinaryOperator<T> accumulator); : reduce()는 T를 받아 연산 후 T를 반환한다. 때문에
            map이나 mapToInt로 타입을 int로 변환시켜주는 것이 필요하겠다.
            => reduce() 메서드가 파라미터로 어떤 함수형 인터페이스를 받는지 확인해볼 것.
            => 해당 함수형 인터페이스는 어떤 것을 받고, 어떤 것을 반환하는지 확인해볼 것.
            => reduce()말고 sum()도 있는데, 이것이 뭐로 구현되어있는지도 볼 것.
            """)
    @Test
    public void reduce1() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 입력)
        int priceTotalSum = 0;
        //then
        Assertions.assertThat(priceTotalSum)
                .isEqualTo(147000);
    }

    @DisplayName("""
            <<reduce() 최대값 구하기>>
            사과 상자에는 100개의 사과가 있다.
            이 사과들 중 가장 비싼 사과가 얼마인지 알고 싶다!
            모든 사과들 중 가장 비싼 사과의 가격을 reduce()를 사용해 구하여라.
            mapToInt(Apple::getPrice)를 사용하여 사과의 가격으로 매핑 후 reduce를 사용하여라.
            => 초깃값을 2000으로 주고 연산하여라!
            
            => T reduce(T identity, BinaryOperator<T> accumulator); : reduce()는 T를 받아 연산 후 T를 반환한다. 때문에
            map이나 mapToInt로 타입을 int로 변환시켜주는 것이 필요하겠다.
            => reduce() 메서드가 파라미터로 어떤 함수형 인터페이스를 받는지 확인해볼 것.
            => 해당 함수형 인터페이스는 어떤 것을 받고, 어떤 것을 반환하는지 확인해볼 것.
            """)
    @Test
    public void reduce2() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 입력)
        int maxPrice = 0;

        //then
        Assertions.assertThat(maxPrice)
                .isEqualTo(2000);
    }
}























/*
1번
            apples.stream()
                .mapToInt(Apple::getPrice).reduce(0, Integer::sum);

2번
            apples.stream()
                .map(Apple::getPrice).reduce(0, Integer::max);
 */
