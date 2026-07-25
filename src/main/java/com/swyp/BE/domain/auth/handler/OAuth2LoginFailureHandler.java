package com.swyp.BE.domain.auth.handler;

import com.swyp.BE.domain.auth.oauth.RedirectOriginResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

    private final RedirectOriginResolver redirectOriginResolver;

    @Value("${app.oauth2.default-redirect-origin}")
    private String defaultRedirectOrigin;

    @Value("${app.oauth2.failure-redirect-path}")
    private String failureRedirectPath;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.warn("OAuth2 로그인 실패: {}", exception.getMessage());

        String origin = redirectOriginResolver.extractOrigin(request.getParameter("state"));
        String failureRedirectUri = (origin != null ? origin : defaultRedirectOrigin) + failureRedirectPath;

        response.sendRedirect(failureRedirectUri);
    }
}