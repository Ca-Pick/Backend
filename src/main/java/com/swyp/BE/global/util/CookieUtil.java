package com.swyp.BE.global.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    @Value("${app.cookie.secure}")
    private boolean secure;

    @Value("${app.cookie.same-site}")
    private String sameSite;

    @Value("${app.cookie.domain:}")
    private String domain;

    public ResponseCookie createAccessTokenCookie(String token, long maxAgeSeconds) {
        return buildCookie("accessToken", token, maxAgeSeconds);
    }

    public ResponseCookie createRefreshTokenCookie(String token, long maxAgeSeconds) {
        return buildCookie("refreshToken", token, maxAgeSeconds);
    }

    public ResponseCookie deleteAccessTokenCookie() {
        return buildCookie("accessToken", "", 0);
    }

    public ResponseCookie deleteRefreshTokenCookie() {
        return buildCookie("refreshToken", "", 0);
    }

    private ResponseCookie buildCookie(String name, String value, long maxAgeSeconds) {
        ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(secure)
                .sameSite(sameSite)
                .path("/")
                .maxAge(maxAgeSeconds);

        if (domain != null && !domain.isBlank()) {
            builder.domain(domain);
        }

        return builder.build();
    }
}