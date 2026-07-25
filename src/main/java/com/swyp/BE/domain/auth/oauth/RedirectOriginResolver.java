package com.swyp.BE.domain.auth.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
public class RedirectOriginResolver {

    private static final String STATE_DELIMITER = ".";

    private final List<String> allowedOrigins;

    public RedirectOriginResolver(@Value("${app.cors.allowed-origin}") String allowedOriginProperty) {
        this.allowedOrigins = Arrays.stream(allowedOriginProperty.split(","))
                .map(String::trim)
                .toList();
    }

    public String buildState(String requestedOrigin) {
        String randomState = UUID.randomUUID().toString();
        if (requestedOrigin != null && allowedOrigins.contains(requestedOrigin)) {
            String encodedOrigin = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(requestedOrigin.getBytes(StandardCharsets.UTF_8));
            return randomState + STATE_DELIMITER + encodedOrigin;
        }
        return randomState;
    }

    public String extractOrigin(String state) {
        if (state == null || !state.contains(STATE_DELIMITER)) {
            return null;
        }
        String encodedOrigin = state.substring(state.indexOf(STATE_DELIMITER) + 1);
        String candidate;
        try {
            candidate = new String(Base64.getUrlDecoder().decode(encodedOrigin), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return allowedOrigins.contains(candidate) ? candidate : null;
    }
}
