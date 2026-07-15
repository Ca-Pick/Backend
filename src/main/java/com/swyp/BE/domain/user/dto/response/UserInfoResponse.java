package com.swyp.BE.domain.user.dto.response;

import com.swyp.BE.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private Long id;
    private String nickname;
    private String provider;
    private String role;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .provider(user.getProvider())
                .role(user.getRole().name())
                .build();
    }
}
