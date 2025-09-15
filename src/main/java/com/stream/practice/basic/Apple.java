package com.stream.practice.basic;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Apple {
    private final int weight;
    private final Color color;
    private final SugarGrade sugarGrade;
    private final int price;

    @Builder(toBuilder = true)
    private Apple(int weight, Color color, SugarGrade sugarGrade, int price) {
        this.weight = weight;
        this.color = color;
        this.sugarGrade = sugarGrade;
        this.price = price;
    }

    public Apple withWeight(int weight) {
        return this.toBuilder()
                .weight(weight)
                .build();
    }

    public Apple repriceBy(int repriceAmount) {
        return this.toBuilder()
                .price(this.price + repriceAmount)
                .build();
    }

    public boolean isSweeterThan(SugarGrade baseSugarGrade) {
        return baseSugarGrade.getGrade() < sugarGrade.getGrade();
    }

    public boolean isExpensiveThan(int basePrice) {
        return basePrice <= price;
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
