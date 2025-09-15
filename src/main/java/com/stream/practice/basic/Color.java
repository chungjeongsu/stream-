package com.stream.practice.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {
    RED("빨강"),
    GREEN("초록"),
    WHITE("흰"),
    BLACK("흑");

    private final String description;
}
