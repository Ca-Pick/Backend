package com.swyp.BE.domain.reference.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchRequest {

    @NotBlank(message = "지역은 필수입니다.")
    private String place;

    @NotBlank(message = "대상은 필수입니다.")
    private String target;

    @NotBlank(message = "케이크 형태는 필수입니다.")
    private String shape;

    @NotBlank(message = "색감은 필수입니다.")
    private String color;

    @NotEmpty(message = "분위기를 한 개 이상 선택해주세요.")
    private String mood;

    @Size(max = 10, message = "장식 태그는 최대 10개까지 선택할 수 있습니다.")
    private List<String> detailTags;
}