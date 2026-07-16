package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.response.RecommendResponse;
import com.swyp.BE.domain.reference.entity.CakeReference;
import com.swyp.BE.domain.reference.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendReferenceUseCase {

    private final ReferenceRepository referenceRepository;

    public RecommendResponse excute() {
        List<CakeReference> birthday = referenceRepository.findBirthday();
        List<CakeReference> celebration = referenceRepository.findCelebration();
        List<CakeReference> academic = referenceRepository.findAcademic();

        List<RecommendResponse.ThemeInfo> births = birthday.stream()
                .map(birth -> RecommendResponse.ThemeInfo.of(
                        birth.getId(),
                        birth.getInstagramEmbed()))
                .toList();

        List<RecommendResponse.ThemeInfo> celebrates = celebration.stream()
                .map(celebrate -> RecommendResponse.ThemeInfo.of(
                        celebrate.getId(),
                        celebrate.getInstagramEmbed()))
                .toList();

        List<RecommendResponse.ThemeInfo> acades = academic.stream()
                .map(acade -> RecommendResponse.ThemeInfo.of(
                        acade.getId(),
                        acade.getInstagramEmbed()))
                .toList();

        return RecommendResponse.from(births, celebrates, acades);

    }
}
