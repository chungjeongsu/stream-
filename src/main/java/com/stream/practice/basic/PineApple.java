package com.stream.practice.basic;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PineApple {
    private final int weight;
    private final Color color;
    private final SugarGrade sugarGrade;
    private final int price;

    @Builder(toBuilder = true)
    private PineApple(int weight, Color color, SugarGrade sugarGrade, int price) {
        this.weight = weight;
        this.color = color;
        this.sugarGrade = sugarGrade;
        this.price = price;
    }

    public static Apple of(int weight, Color color, SugarGrade sugarGrade, int price){
        return Apple.builder()
                .weight(weight)
                .color(color)
                .sugarGrade(sugarGrade)
                .price(price)
                .build();
    }
}
