package com.stream.practice.stream;

import com.stream.practice.basic.Apple;
import com.stream.practice.basic.AppleBox;
import com.stream.practice.basic.SugarGrade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

import java.util.List;
import java.util.function.Predicate;

import static com.stream.practice.basic.SugarGrade.*;

@SpringBootTest
public class AFilterTest {
    /**
     * 기초 : Stream의 filter() 메서드를 이용해 필터링한다.
     * => filter는 원본을 변경하지 않음
     * => filter는 중간 연산임(중간 vs 최종)
     * basic 패키지에 Apple AppleBox 간단 구현해놨슴다.
     * 제 답은 아래에 있습니다.
     */
    @Autowired
    private AppleBox appleBox;

    // @Autowired
    // private AnnotationMBeanExporter annotationMBeanExporter;

    @DisplayName("""
            <<기본 filter()>>
            사과 상자에는 100개의 사과가 있다.
            당도는 SugarGrade.HIGH, SugarGrade.MIDDLE, SugarGrade.LOW 이렇게 있다.
            이들 중 SugarGrade가 HIGH인 것들만을 필터링하여라.
            Apple의 isSweeterThan()를 사용하여라.
            List<Apple>로 반환하여라.
            불변 리스트로 반환하여라.
            
            => filter() 메서드에 Command + Option + B를 눌러 어떤 함수형 인터페이스를 받고 있는지 확인해볼 것.
            => 해당 함수형 인터페이스는 뭘 받고 뭘 반환하는지 확인할 것.(제네릭, 함수형 인터페이스, 람다(익명 메서드) 등)
            """)
    @Test
    void filter1() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when (정답코드 작성)
        List<Apple> sweeterApples = apples.stream()
                .filter(apple -> apple.isSweeterThan(MIDDLE) && apple.isSweeterThan(LOW))
                .toList();

        //then
        Assertions.assertThat(sweeterApples)
                .hasSize(27)                //27개인가?
                .extracting(Apple::getSugarGrade)   //sugarGrade만 추출
                .containsOnly(HIGH);     //High만 포함하고 있는가?
    }

    @DisplayName("""
            <<이중 filter()>>
            사과 상자에는 100개의 사과가 있다.
            이번에는 SugarGrade가 High인 것 뿐만 아니라, price까지 1200원 이상인 것을 분류하고 싶다.
            이들 중 SugarGrade가 High인 것과 price가 1200원 이상인 것들만을 필터링하여라.
            Apple의 isSweeterThan()를 사용하여라.
            Apple의 isExpensiveThan()를 사용하여라.
            List<Apple>로 반환하여라.
            불변 리스트로 반환하여라.
            """)
    @Test
    public void filter2() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when (정답코드 작성)
        List<Apple> sweeterAndExpensiveApples = apples.stream()
                .filter(apple -> apple.isSweeterThan(MIDDLE) && apple.isSweeterThan(LOW))
                .filter(apple -> apple.isExpensiveThan(1200))
                .toList();

        //then
        Assertions.assertThat(sweeterAndExpensiveApples)
                .hasSize(21)
                .allSatisfy(apple -> {
                    Assertions.assertThat(apple.getSugarGrade()).isEqualTo(HIGH);
                    Assertions.assertThat(apple.getPrice()).isGreaterThanOrEqualTo(1200);
                });
    }

    @DisplayName("""
            <<함수 합성 filter()>>
            사과 상자에는 100개의 사과가 있다.
            이번에는 SugarGrade가 High인 것 뿐만 아니라, price까지 1200원 이상인 것을 분류하고 싶다.
            이들 중 SugarGrade가 High인 것과 price가 1200원 이상인 것들만을 필터링하여라.
            Apple의 isSweeterThan()를 사용하여라.
            Apple의 isExpensiveThan()를 사용하여라.
            List<Apple>로 반환하여라.
            불변 리스트로 반환하여라.
            + filter는 한 개만 사용하여라.(아래의 predicate와 함수 합성을 이용하여라)
            """)
    @Test
    public void filter3() {
        //given
        List<Apple> apples = appleBox.getApples();

        Predicate<Apple> sweeter = apple -> apple.isSweeterThan(MIDDLE);
        Predicate<Apple> expensive = apple -> apple.isExpensiveThan(1200);

        //when (정답코드 작성)
        List<Apple> sweeterAndExpensiveApples = apples.stream()
                .filter(sweeter.and(expensive))
                .toList();

        //then
        Assertions.assertThat(sweeterAndExpensiveApples)
                .hasSize(21)
                .allSatisfy(apple -> {
                    Assertions.assertThat(apple.getSugarGrade()).isEqualTo(HIGH);
                    Assertions.assertThat(apple.getPrice()).isGreaterThanOrEqualTo(1200);
                });
    }
}



























/*
1번
        List<Apple> sweeterApples = apples
                .stream()
                .filter(apple -> apple.isSweeterThan(SugarGrade.MIDDLE))
                .toList();


2번
        List<Apple> sweeterAndExpensiveApples = apples.stream()
                .filter(apple -> apple.isSweeterThan(SugarGrade.MIDDLE))
                .filter(apple -> apple.isExpensiveThan(1200))
                .toList();


3번
        Predicate<Apple> sweeter = apple -> apple.isSweeterThan(SugarGrade.MIDDLE);
        Predicate<Apple> expensive = apple -> apple.isExpensiveThan(1200);

        List<Apple> sweeterAndExpensiveApples = apples.stream()
                .filter(sweeter.and(expensive))
                .toList();


 */
