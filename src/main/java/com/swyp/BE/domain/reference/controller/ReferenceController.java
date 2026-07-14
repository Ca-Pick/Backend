package com.swyp.BE.domain.reference.controller;


import com.swyp.BE.domain.reference.dto.request.SearchRequest;
import com.swyp.BE.domain.reference.dto.response.SearchResponse;
import com.swyp.BE.domain.reference.service.SearchReferenceUseCase;
import com.swyp.BE.global.documention.ReferenceApiDocumentation;
import com.swyp.BE.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final SearchReferenceUseCase searchReferenceUseCase;


    @ReferenceApiDocumentation.CakeDoc
    @GetMapping("/search")
    public ApiResponse<SearchResponse> searchReferences(@Valid @RequestBody SearchRequest request) {

        return ApiResponse.success(searchReferenceUseCase.excute(request));
    }
}


