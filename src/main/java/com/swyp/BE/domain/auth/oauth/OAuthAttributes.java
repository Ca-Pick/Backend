package com.swyp.BE.domain.auth.oauth;

import com.swyp.BE.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private final String provider;
    private final String providerId;
    private final String nickname;

    @Builder
    private OAuthAttributes(String provider, String providerId, String nickname) {
        this.provider = provider;
        this.providerId = providerId;
        this.nickname = nickname;
    }

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver(attributes);
        }
        return ofKakao(attributes);
    }

    private static OAuthAttributes ofKakao(Map<String, Object> attributes) {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        return OAuthAttributes.builder()
                .provider("kakao")
                .providerId(String.valueOf(attributes.get("id")))
                .nickname((String) properties.get("nickname"))
                .build();
    }

    private static OAuthAttributes ofNaver(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .provider("naver")
                .providerId((String) response.get("id"))
                .nickname((String) response.get("nickname"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .provider(provider)
                .providerId(providerId)
                .nickname(nickname)
                .build();
    }
}