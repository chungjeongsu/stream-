package com.stream.practice.stream;

import com.stream.practice.basic.Apple;
import com.stream.practice.basic.AppleBox;
import com.stream.practice.basic.dto.AppleMapDTO;
import com.stream.practice.basic.SugarGrade;
import com.stream.practice.basic.dto.AppleMapDTOs;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BMapTest {

    @Autowired
    private AppleBox appleBox;

    @DisplayName("""
            <<특정 필드 추출>>
            사과 상자에는 100개의 사과가 있다.
            이 중, 당도들만을 추출한 List를 만들고 싶다.
            map을 사용해서 당도들만 있는 불변 리스트를 만들어라.
            
            => map() 메서드가 파라미터로 어떤 함수형 인터페이스를 받는지 확인해볼 것.
            => 해당 함수형 인터페이스는 어떤 것을 받고, 어떤 것을 반환하는지 확인해볼 것.
            """)
    @Test
    void map1() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<SugarGrade> sugarGrades = null;

        //then
        Assertions.assertThat(sugarGrades)
                .containsAll(List.of(SugarGrade.HIGH, SugarGrade.LOW, SugarGrade.MIDDLE));
    }

    @DisplayName("""
            <<값 가공>>
            사과 상자에는 100개의 사과가 있다.
            물가 상승에 맞춰 모든 사과의 가격에 100원을 더하려고 한다.
            이 사과들의 가격을 모두 100원씩 올려라!
            Apple 클래스의 repriceBy를 이용하라. 파라미터로는 바꿀 가격을 받는다.
            """)
    @Test
    void map2() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<Apple> repricedApples = null;

        //then
        Assertions.assertThat(repricedApples)
                .zipSatisfy(apples, (repricedApple, apple) -> {
                    Assertions.assertThat(repricedApple.getPrice())
                            .isEqualTo(apple.getPrice() + 100);
                });
    }

    @DisplayName("""
            <<DTO 매핑 블록 사용 return>>
            사과 상자에는 100개의 사과가 있다.
            이를 클라이언트에 제공하기 위해 DTO로 매핑하고 싶다.
            basic/dto 패키지의 AppleMapDTO를 보고 매핑해보자.
            enum타입은 각각 바로 매핑이 아니라 Color color => String color, SugarGrade sugarGrade => int sugarGrade로 매핑한다.
            
            => enum Color는 color.getDescription()을 사용해 매핑하여라.
            => enum SugarGrade는 sugarGrade.getGrade()를 사용해 매핑하여라.
            """)
    @Test
    public void map3() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        List<AppleMapDTO> appleMapDTOs = null;

        //then
        Assertions.assertThat(appleMapDTOs)
                .zipSatisfy(apples, (appleMapDTO, apple) -> {
                    Assertions.assertThat(appleMapDTO)
                            .extracting("price", "color", "sugarGrade", "price")
                            .contains(
                                    apple.getPrice(), apple.getColor().getDescription(), apple.getSugarGrade().getGrade(), apple.getPrice()
                            );
                });
    }

    @DisplayName("""
            <<DTO 매핑 실습 1>>
            사과 상자에는 100개의 사과가 있다.
            이를 클라이언트에 제공하기 위해 DTO로 매핑하고 싶다.
            위처럼 매핑하는 방식은 코드가 너무 더러워진다.
            때문에, 매핑 팩터리 메서드를 직접 DTO에 작성해 사용한다.
            이제 AppleMapDTO에 가서 직접 매핑 메서드를 작성해보고, 정답 코드를 작성해본다.
            
            => 매핑은 DTO에 설명글이 써져있다.
            **Stream에서는 3번의 코드 블록처럼 늘어놓는 것보다, 메서드를 만들어 캡슐화를 지켜주는 것이 가독성이 좋을 것이다.**
            => 어띃게 생각하심까??
            """)
    @Test
    public void map4() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답 코드 작성)
        List<AppleMapDTO> appleMapDTOs = null;

        //then
        Assertions.assertThat(appleMapDTOs)
                .zipSatisfy(apples, (appleMapDTO, apple) -> {
                    Assertions.assertThat(appleMapDTO)
                            .extracting("price", "color", "sugarGrade", "price")
                            .contains(
                                    apple.getPrice(), apple.getColor().getDescription(), apple.getSugarGrade().getGrade(), apple.getPrice()
                            );
                });
    }

    @DisplayName("""
            <<DTO 매핑 실습 2, 일급 컬렉션>>
            사과 상자에는 100개의 사과가 있다.
            이를 클라이언트에 제공하기 위해 DTO로 매핑하고 싶다.
            이미 AppleMapDTO로는 만들었다.
            하지만, 악덕 프론트가 일급 컬렉션으로 매핑해달라고 한다.
            때문에, 매핑 팩터리 메서드를 직접 AppleMapDTOs에 작성해 사용한다.
            이제 AppleMapDTOs에 가서 직접 매핑 메서드를 작성해보고, 정답코드를 작성해본다.
            
            => 매핑은 DTO에 설명글이 써져있다.
            """)
    @Test
    public void map5() {
        //given
        List<Apple> apples = appleBox.getApples();

        //when(정답코드 작성)
        AppleMapDTOs appleMapDTOs = null;

        //then
        Assertions.assertThat(appleMapDTOs.getAppleMapDTOs())
                .zipSatisfy(apples, (appleMapDTO, apple) -> {
                    Assertions.assertThat(appleMapDTO)
                            .extracting("price", "color", "sugarGrade", "price")
                            .contains(
                                    apple.getPrice(), apple.getColor().getDescription(), apple.getSugarGrade().getGrade(), apple.getPrice()
                            );
                });
    }
}











/*
1번
            apples.stream()
                .map(Apple::getSugarGrade)
                .toList();

2번
            apples.stream()
                .map(apple -> apple.repriceBy(100))
                .toList();

3번
            apples.stream()
                .map(apple -> {
                    return AppleMapDTO.builder()
                            .weight(apple.getWeight())
                            .color(apple.getColor().getDescription())
                            .sugarGrade(apple.getSugarGrade().getGrade())
                            .price(apple.getPrice())
                            .build();
                })
                .toList();

4번
            apples.stream()
                .map(AppleMapDTO::from)
                .toList();

5번
            AppleMapDTOs.from(apples);
 */