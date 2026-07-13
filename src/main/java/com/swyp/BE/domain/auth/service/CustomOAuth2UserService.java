package com.swyp.BE.domain.auth.service;

import com.swyp.BE.domain.auth.oauth.OAuthAttributes;
import com.swyp.BE.domain.user.dto.CustomOAuth2User;
import com.swyp.BE.domain.user.entity.User;
import com.swyp.BE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, oAuth2User.getAttributes());

        User user = userRepository.findByProviderAndProviderId(attributes.getProvider(), attributes.getProviderId())
                .map(existing -> existing.updateNickname(attributes.getNickname()))
                .orElseGet(() -> userRepository.save(attributes.toEntity()));

        return new CustomOAuth2User(user.getId(), user.getRole(), oAuth2User.getAttributes());
    }
}
