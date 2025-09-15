package com.stream.practice.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SugarGrade {
    HIGH(3), MIDDLE(2), LOW(1);

    private final int grade;
}
