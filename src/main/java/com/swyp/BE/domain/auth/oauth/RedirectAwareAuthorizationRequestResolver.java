package com.swyp.BE.domain.auth.oauth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
public class RedirectAwareAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private static final String AUTHORIZATION_BASE_URI = "/api/oauth2/authorization";
    private static final String REDIRECT_URI_PARAM = "redirect_uri";

    private final DefaultOAuth2AuthorizationRequestResolver defaultResolver;
    private final RedirectOriginResolver redirectOriginResolver;

    public RedirectAwareAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository,
                                                       RedirectOriginResolver redirectOriginResolver) {
        this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository, AUTHORIZATION_BASE_URI);
        this.redirectOriginResolver = redirectOriginResolver;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        return customize(defaultResolver.resolve(request), request);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        return customize(defaultResolver.resolve(request, clientRegistrationId), request);
    }

    private OAuth2AuthorizationRequest customize(OAuth2AuthorizationRequest authorizationRequest,
                                                  HttpServletRequest request) {
        if (authorizationRequest == null) {
            return null;
        }

        String requestedOrigin = request.getParameter(REDIRECT_URI_PARAM);
        String state = redirectOriginResolver.buildState(requestedOrigin);

        return OAuth2AuthorizationRequest.from(authorizationRequest)
                .state(state)
                .build();
    }
}
