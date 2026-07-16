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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final SearchReferenceUseCase searchReferenceUseCase;
    private final DetailReferenceUseCase detailReferenceUseCase;
    private final RecommendReferenceUseCase recommendReferenceUseCase;


    @ReferenceApiDocumentation.CakeDoc
    @PostMapping("/search")
    public ApiResponse<SearchResponse> searchReferences(@Valid @RequestBody SearchRequest request) {

        return ApiResponse.success(searchReferenceUseCase.excute(request));
    }

    @ReferenceApiDocumentation.DetailCakeDoc
    @GetMapping("/{referenceId}")
    public ApiResponse<DetailResponse> detailReference(@PathVariable Long referenceId) {

        return ApiResponse.success(detailReferenceUseCase.excute(referenceId));
    }

    @ReferenceApiDocumentation.RecommendCakeDoc
    @GetMapping("/recommend")
    public ApiResponse<RecommendResponse> recommendReference() {

        return ApiResponse.success(recommendReferenceUseCase.excute());
    }


}


