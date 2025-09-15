package com.stream.practice.basic.dto;

import com.stream.practice.basic.Apple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AppleMapDTOs {
    private List<AppleMapDTO> appleMapDTOs;

    //Stream과 AppleMapDTO에 작성한 생성 메서드(팩터리 or 생성자 이용)을 사용해 작성해본다.(map이용)
    //AppleMapDTO와 같이 두가지 방법이 있겠다. 아래에 예시 코드 있음.
}

























/*
팩터리메서드 이용방법(빌더 사용)
    public static AppleMapDTOs from(List<Apple> apples) {
        return AppleMapDTOs.builder()
                .appleMapDTOs(
                        apples.stream().map(AppleMapDTO::from).toList()
                )
                .build();
    }

팩터리 메서드 이용 방법(생성자)
public static AppleMapDTOs from(List<Apple> apples) {
        return new AppleMapDTOs(
                apples.stream().map(AppleMapDTO::from).toList()
        );
    }

 */
