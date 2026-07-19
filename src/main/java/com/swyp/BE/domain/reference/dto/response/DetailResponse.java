package com.swyp.BE.domain.reference.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class DetailResponse {

    private String instagramEmbed;

    private String name;

    private List<String> tags;

    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    private String instagramUrl;

    private List<DetailResponse.CakeListInfo> cakelists;


    @Getter
    @AllArgsConstructor
    public static class CakeListInfo {

        private Long cakeId;
        private String instagramEmbed;
        private boolean saved;

        public static DetailResponse.CakeListInfo of(Long cakeId, String instagramEmbed, boolean saved) {
            return new DetailResponse.CakeListInfo(cakeId, instagramEmbed, saved);
        }
    }

    public static DetailResponse from(String instagramEmbed, String name,
                                      List<String> tags, String address, BigDecimal latitude, BigDecimal longitude,
                                      String instagramUrl, List<DetailResponse.CakeListInfo> cakes) {
        return DetailResponse.builder()
                .instagramEmbed(instagramEmbed)
                .name(name)
                .tags(tags)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .instagramUrl(instagramUrl)
                .cakelists(cakes)
                .build();
    }
}
