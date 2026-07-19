package com.swyp.BE.domain.save.controller;


import com.swyp.BE.domain.save.dto.response.SaveListResponse;
import com.swyp.BE.domain.save.service.SaveService;
import com.swyp.BE.global.documention.SaveApiDocumentation;
import com.swyp.BE.global.exception.BusinessException;
import com.swyp.BE.global.exception.ErrorCode;
import com.swyp.BE.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/save")
@RequiredArgsConstructor
public class SaveController {

    private final SaveService saveService;

    @SaveApiDocumentation.SavePostDoc
    @PostMapping("/{referenceId}/like")
    public ApiResponse<Void> createSave(Authentication authentication, @PathVariable Long referenceId) {
        Long userId = resolveUserId(authentication);

        saveService.save(userId, referenceId);
        return ApiResponse.success();
    }

    @SaveApiDocumentation.SaveDeleteDoc
    @DeleteMapping("/{referenceId}/like")
    public ApiResponse<Void> cancelSave(Authentication authentication, @PathVariable Long referenceId) {
        Long userId = resolveUserId(authentication);

        saveService.cancel(userId, referenceId);
        return ApiResponse.success();
    }

    @SaveApiDocumentation.SaveListDoc
    @GetMapping
    public ApiResponse<SaveListResponse> getMySaves(Authentication authentication) {
        Long userId = resolveUserId(authentication);

        return ApiResponse.success(saveService.getMySaves(userId));
    }

    private Long resolveUserId(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BusinessException(ErrorCode.UNAUTHENTICATED);
        }
        return (Long) authentication.getPrincipal();
    }
}
