package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.response.DecorationsResponse;
import com.swyp.BE.domain.reference.repository.DetailReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailTagsUseCase {

    private final DetailReferenceRepository detailReferenceRepository;

    public DecorationsResponse excute() {

        return DecorationsResponse.from(detailReferenceRepository.findDistinctDecorations());
    }
}
