package com.swyp.BE.domain.user.controller;

import com.swyp.BE.domain.user.dto.request.NicknameUpdateRequest;
import com.swyp.BE.domain.user.dto.response.UserInfoResponse;
import com.swyp.BE.domain.user.service.UserService;
import com.swyp.BE.global.documention.UserApiDocumentation;
import com.swyp.BE.global.exception.BusinessException;
import com.swyp.BE.global.exception.ErrorCode;
import com.swyp.BE.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swyp.BE.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final com.swyp.BE.global.util.CookieUtil cookieUtil;

    @UserApiDocumentation.UserInfoDoc
    @GetMapping("/me")
    public ApiResponse<UserInfoResponse> getMyInfo(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BusinessException(ErrorCode.UNAUTHENTICATED);
        }
        Long userId = (Long) authentication.getPrincipal();

        return ApiResponse.success(userService.getMyInfo(userId));
    }

    @UserApiDocumentation.NicknameUpdateDoc
    @PatchMapping("/nickname")
    public ApiResponse<Void> updateNickname(
            Authentication authentication,
            @Valid @RequestBody NicknameUpdateRequest request) {

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BusinessException(ErrorCode.UNAUTHENTICATED);
        }
        Long userId = (Long) authentication.getPrincipal();

        userService.updateNickname(userId, request.getNickname());
        return ApiResponse.success();
    }

    @UserApiDocumentation.WithdrawDoc
    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> withdraw(
            Authentication authentication,
            HttpServletResponse response) {

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BusinessException(ErrorCode.UNAUTHENTICATED);
        }
        Long userId = (Long) authentication.getPrincipal();

        userService.withdraw(userId);

        response.addHeader(HttpHeaders.SET_COOKIE, cookieUtil.deleteAccessTokenCookie().toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookieUtil.deleteRefreshTokenCookie().toString());

        return ResponseEntity.ok(ApiResponse.success());
    }
}
