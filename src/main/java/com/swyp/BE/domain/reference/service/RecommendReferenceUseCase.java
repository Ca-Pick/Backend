package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.response.RecommendResponse;
import com.swyp.BE.domain.reference.entity.CakeReference;
import com.swyp.BE.domain.reference.repository.CakeSaveRepository;
import com.swyp.BE.domain.reference.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendReferenceUseCase {

    private final ReferenceRepository referenceRepository;
    private final CakeSaveRepository cakeSaveRepository;

    public RecommendResponse excute(Long userId) {
        List<CakeReference> birthday = referenceRepository.findBirthday();
        List<CakeReference> celebration = referenceRepository.findCelebration();
        List<CakeReference> academic = referenceRepository.findAcademic();

        List<RecommendResponse.ThemeInfo> births = birthday.stream()
                .map(birth ->{
                    boolean saved = false;
                    if (userId != null) {
                        saved = cakeSaveRepository.existsByUserIdAndCakeReferenceId(userId, birth.getId());
                    }

                    return RecommendResponse.ThemeInfo.of(birth.getId(), birth.getInstagramEmbed(), saved);

                }).toList();

        List<RecommendResponse.ThemeInfo> celebrates = celebration.stream()
                .map(celebrate -> {
                    boolean saved = false;
                    if (userId != null) {
                        saved = cakeSaveRepository.existsByUserIdAndCakeReferenceId(userId, celebrate.getId());
                    }

                    return RecommendResponse.ThemeInfo.of(celebrate.getId(), celebrate.getInstagramEmbed(), saved);

                }).toList();

        List<RecommendResponse.ThemeInfo> acades = academic.stream()
                .map(acade -> {
                    boolean saved = false;
                    if (userId != null) {
                        saved = cakeSaveRepository.existsByUserIdAndCakeReferenceId(userId, acade.getId());
                    }

                    return RecommendResponse.ThemeInfo.of(acade.getId(), acade.getInstagramEmbed(), saved);

                }).toList();

        return RecommendResponse.from(births, celebrates, acades);

    }
}
