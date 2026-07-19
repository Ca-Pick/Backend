package com.swyp.BE.domain.reference.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecommendResponse {

    private List<RecommendResponse.ThemeInfo> birthday;
    private List<RecommendResponse.ThemeInfo> celebration;
    private List<RecommendResponse.ThemeInfo> academic;


    @Getter
    @AllArgsConstructor
    public static class ThemeInfo {

        private Long cakeId;
        private String instagramEmbed;
        private boolean saved;

        public static RecommendResponse.ThemeInfo of(Long cakeId, String instagramEmbed, boolean saved) {
            return new RecommendResponse.ThemeInfo(cakeId, instagramEmbed, saved);
        }
    }

    public static RecommendResponse from(List<RecommendResponse.ThemeInfo> birthday, List<RecommendResponse.ThemeInfo> celebration, List<RecommendResponse.ThemeInfo> academic) {
        return RecommendResponse.builder()
                .birthday(birthday)
                .celebration(celebration)
                .academic(academic)
                .build();
    }
}
