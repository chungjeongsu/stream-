package com.stream.practice.basic.dto;

import com.stream.practice.basic.Apple;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleMapDTO {
    private int weight;
    private String color;
    private int sugarGrade;
    private int price;

    // 여기에 매핑 메서드를 작성해본다. 여러가지 방법들 예시는 아래에 있다.

    public static AppleMapDTO from(Apple apple){
        return AppleMapDTO.builder()
                .weight(apple.getWeight())
                .color(apple.getColor().getDescription())
                .sugarGrade(apple.getSugarGrade().getGrade())
                .price(apple.getPrice())
                .build();
    }
}





























/*
정적 팩터리 메서드를 이용하는 방법(Builder 이용)
    public static AppleMapDTO from(final Apple apple) {
        return AppleMapDTO.builder()
                .weight(apple.getWeight())
                .color(apple.getColor().getDescription())
                .sugarGrade(apple.getSugarGrade().getGrade())
                .price(apple.getPrice())
                .build();
    }

정적 팩터리 메서드를 이용하는 방법(생성자 이용)
public static AppleMapDTO of(final Apple apple) {
    return new AppleMapDTO(apple.getWeight(), apple.getColor().getDescription(), apple.getSugarGrade().getGrade(), apple.getPrice());
}

그냥 생성자 이용 => 여기서 구현 X
 */