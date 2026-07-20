package com.swyp.BE.domain.reference.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DecorationsResponse {

    private List<String> decorations;

    public static DecorationsResponse from(List<String> decorations) {
        return DecorationsResponse.builder()
                .decorations(decorations)
                .build();
    }
}
