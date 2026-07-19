package com.swyp.BE.domain.save.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SaveListResponse {

    private List<SavedCakeInfo> cakes;

    @Getter
    @AllArgsConstructor
    public static class SavedCakeInfo {

        private Long cakeId;
        private String instagramEmbed;
        private List<String> cakedetailtags;

        public static SavedCakeInfo of(Long cakeId, String instagramEmbed, List<String> cakedetailtags) {
            return new SavedCakeInfo(cakeId, instagramEmbed, cakedetailtags);
        }
    }

    public static SaveListResponse from(List<SavedCakeInfo> cakes) {
        return SaveListResponse.builder()
                .cakes(cakes)
                .build();
    }
}
