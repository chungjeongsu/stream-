package com.stream.practice.stream;

import com.stream.practice.basic.AppleBox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            => 색깔이 RED이고, 
            => 가격이 1300원 이상인 사과들의 
            => weight만 모아 List<Integer>로 만들어라    
            """)
    @Test
    public void mix1() {

    }

    @DisplayName("""
            => 색깔이 GREEN인 사과들을
            => 가격 기준 내림차순으로 정렬하여라. 
            """)
    @Test
    public void mix2() {

    }

    @DisplayName("""
            => 색깔이 GREEN인 사과들을
            => 가격 기준 내림차순으로 정렬하여라.
            """)
    @Test
    public void mix3() {

    }

    @DisplayName("""
            => 당도가 HIGH인 사과들의
            => 총 가격을 구하라
            """)
    @Test
    public void mix4() {

    }

    @DisplayName("""
            => 가장 무거운 사과의 무게를 찾아라.
            """)
    @Test
    public void mix5() {

    }

    @DisplayName("""
            => 가장 무거운 사과 3개를 골라
            => 합을 구하여라
            """)
    @Test
    public void mix6() {

    }

    @DisplayName("""
            => 가격이 1500원 이상인 사과들 중
            => 색깔 별 무게 평균을 내서
            => Map<Color, Double>의 형태로 반환하여라. 
            """)
    @Test
    public void mix7() {

    }

    @DisplayName("""
            => 무게가 120 이상인 것들을 골라
            => 가격이 비싼 것들 순으로 정렬하여,
            => List<Color>의 형태로 반환하여라.
            """)
    @Test
    public void mix8() {

    }
}
