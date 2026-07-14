package com.swyp.BE.domain.auth.handler;

import com.swyp.BE.domain.user.dto.CustomOAuth2User;
import com.swyp.BE.global.jwt.JwtProvider;
import com.swyp.BE.global.jwt.RefreshTokenRepository;
import com.swyp.BE.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieUtil cookieUtil;

    @Value("${app.oauth2.redirect-uri}")
    private String redirectUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomOAuth2User principal = (CustomOAuth2User) authentication.getPrincipal();
        Long userId = principal.getUserId();
        String role = principal.getRole().name();

        String accessToken = jwtProvider.generateAccessToken(userId, role);
        String refreshToken = jwtProvider.generateRefreshToken(userId);

        refreshTokenRepository.save(userId, refreshToken, jwtProvider.getRefreshTokenExpirationMs());

        long accessTokenMaxAgeSeconds = jwtProvider.getAccessTokenExpirationMs() / 1000;
        long refreshTokenMaxAgeSeconds = jwtProvider.getRefreshTokenExpirationMs() / 1000;

        response.addHeader(HttpHeaders.SET_COOKIE,
                cookieUtil.createAccessTokenCookie(accessToken, accessTokenMaxAgeSeconds).toString());
        response.addHeader(HttpHeaders.SET_COOKIE,
                cookieUtil.createRefreshTokenCookie(refreshToken, refreshTokenMaxAgeSeconds).toString());

        response.sendRedirect(redirectUri);
    }
}