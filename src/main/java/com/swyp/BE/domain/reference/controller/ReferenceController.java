package com.swyp.BE.domain.reference.controller;


import com.swyp.BE.domain.reference.dto.request.SearchRequest;
import com.swyp.BE.domain.reference.dto.response.DetailResponse;
import com.swyp.BE.domain.reference.dto.response.RecommendResponse;
import com.swyp.BE.domain.reference.dto.response.SearchResponse;
import com.swyp.BE.domain.reference.service.DetailReferenceUseCase;
import com.swyp.BE.domain.reference.service.RecommendReferenceUseCase;
import com.swyp.BE.domain.reference.service.SearchReferenceUseCase;
import com.swyp.BE.global.documention.ReferenceApiDocumentation;
import com.swyp.BE.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final SearchReferenceUseCase searchReferenceUseCase;
    private final DetailReferenceUseCase detailReferenceUseCase;
    private final RecommendReferenceUseCase recommendReferenceUseCase;


    @ReferenceApiDocumentation.CakeDoc
    @PostMapping("/search")
    public ApiResponse<SearchResponse> searchReferences(@AuthenticationPrincipal Long userId, @Valid @RequestBody SearchRequest request) {

        return ApiResponse.success(searchReferenceUseCase.excute(userId, request));
    }

    @ReferenceApiDocumentation.DetailCakeDoc
    @GetMapping("/{referenceId}")
    public ApiResponse<DetailResponse> detailReference(@AuthenticationPrincipal Long userId, @PathVariable Long referenceId) {

        return ApiResponse.success(detailReferenceUseCase.excute(userId, referenceId));
    }

    @ReferenceApiDocumentation.RecommendCakeDoc
    @GetMapping("/recommend")
    public ApiResponse<RecommendResponse> recommendReference(@AuthenticationPrincipal Long userId) {

        return ApiResponse.success(recommendReferenceUseCase.excute(userId));
    }


}


