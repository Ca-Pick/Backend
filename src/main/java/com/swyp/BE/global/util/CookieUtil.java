package com.swyp.BE.global.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

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
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
    }
}