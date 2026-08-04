package com.swyp.BE.domain.user.service;

import com.swyp.BE.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class OAuthUnlinkService {

    private static final String KAKAO_UNLINK_URI = "https://kapi.kakao.com/v1/user/unlink";
    private static final String NAVER_UNLINK_URI = "https://nid.naver.com/oauth2.0/token";

    private final RestClient restClient = RestClient.create();

    private final String kakaoAdminKey;
    private final String naverClientId;
    private final String naverClientSecret;

    public OAuthUnlinkService(
            @Value("${app.kakao.admin-key}") String kakaoAdminKey,
            @Value("${spring.security.oauth2.client.registration.naver.client-id}") String naverClientId,
            @Value("${spring.security.oauth2.client.registration.naver.client-secret}") String naverClientSecret) {
        this.kakaoAdminKey = kakaoAdminKey;
        this.naverClientId = naverClientId;
        this.naverClientSecret = naverClientSecret;
    }

    public void unlink(User user) {
        if ("kakao".equals(user.getProvider())) {
            unlinkKakao(user);
        } else if ("naver".equals(user.getProvider())) {
            unlinkNaver(user);
        } else {
            log.warn("알 수 없는 provider={}, unlink를 건너뜁니다. userId={}", user.getProvider(), user.getId());
        }
    }

    private void unlinkKakao(User user) {
        try {
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("target_id_type", "user_id");
            form.add("target_id", user.getProviderId());

            restClient.post()
                    .uri(KAKAO_UNLINK_URI)
                    .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoAdminKey)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(form)
                    .retrieve()
                    .toBodilessEntity();

            log.info("카카오 연결 끊기 성공. userId={}", user.getId());
        } catch (Exception e) {
            log.warn("카카오 연결 끊기 실패(무시하고 탈퇴 계속 진행). userId={}, message={}", user.getId(), e.getMessage());
        }
    }

    private void unlinkNaver(User user) {
        String naverAccessToken = user.getNaverAccessToken();
        if (naverAccessToken == null || naverAccessToken.isBlank()) {
            log.warn("저장된 네이버 access token이 없어 unlink를 건너뜁니다. userId={}", user.getId());
            return;
        }
        try {
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("client_id", naverClientId);
            form.add("client_secret", naverClientSecret);
            form.add("access_token", naverAccessToken);
            form.add("grant_type", "delete");
            form.add("service_provider", "NAVER");

            restClient.post()
                    .uri(NAVER_UNLINK_URI)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(form)
                    .retrieve()
                    .toBodilessEntity();

            log.info("네이버 연결 끊기 성공. userId={}", user.getId());
        } catch (Exception e) {
            log.warn("네이버 연결 끊기 실패(무시하고 탈퇴 계속 진행). userId={}, message={}", user.getId(), e.getMessage());
        }
    }
}
