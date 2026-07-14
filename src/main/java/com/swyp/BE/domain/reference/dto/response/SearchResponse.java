package com.swyp.BE.domain.reference.dto.response;


import com.swyp.BE.domain.reference.entity.DetailReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SearchResponse {

    private final List<String> tags;
    private final List<InstagramEmbedInfo> cakes;


    @Getter
    @AllArgsConstructor
    public static class InstagramEmbedInfo {

        private String instagramEmbed;
        private List<String> cakedetailtags;

        public static InstagramEmbedInfo of(String instagramEmbed, List<String> cakedetailtags) {
            return new InstagramEmbedInfo(instagramEmbed, cakedetailtags);
        }
    }

    public static SearchResponse from(List<String> tags, List<InstagramEmbedInfo> cakes) {
        return SearchResponse.builder()
                .tags(tags)
                .cakes(cakes)
                .build();
    }
}
