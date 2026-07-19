package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.response.DetailResponse;
import com.swyp.BE.domain.reference.entity.*;
import com.swyp.BE.domain.reference.repository.CakeSaveRepository;
import com.swyp.BE.domain.reference.repository.CakeStoreRepository;
import com.swyp.BE.domain.reference.repository.ReferenceRepository;
import com.swyp.BE.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetailReferenceUseCase {

    private final ReferenceRepository referenceRepository;
    private final CakeStoreRepository cakeStoreRepository;
    private final CakeSaveRepository cakeSaveRepository;

    public DetailResponse excute(Long userId, Long referenceId) {

        CakeReference cake = referenceRepository.findDetailById(referenceId)
                .orElseThrow(BusinessException::referenceNotFound);

        CakeStore cakeStore = cakeStoreRepository.findById(cake.getCakeStore().getId())
                .orElseThrow(BusinessException::referenceNotFound);

        log.info(String.valueOf(userId));

        List<DetailResponse.CakeListInfo> cakelist = cakeStore.getCakeReferences().stream()
                .map(c -> {
                    boolean saved = false;
                    if (userId != null) {
                        saved = cakeSaveRepository.existsByUserIdAndCakeReferenceId(userId, c.getId());
                    }

                    return DetailResponse.CakeListInfo.of(c.getId(), c.getInstagramEmbed(), saved);

                })
                .toList();

        List<String> tags = new ArrayList<>();
        tags.add(cake.getPlace());
        tags.addAll(cake.getTargetReferences().stream()
                .map(TargetReference::getTarget).toList());
        tags.add(cake.getShape());
        tags.add(cake.getColor());
        tags.addAll(cake.getMoodReferences().stream()
                .map(MoodReference::getMood).toList());
        tags.addAll(cake.getDetailReferences().stream()
                .map(DetailReference::getDecoration).toList());

        boolean saved = cakeSaveRepository.existsByUserIdAndCakeReferenceId(userId, referenceId);

        return DetailResponse.from(cake.getId(), cake.getInstagramEmbed(), cake.getCakeStore().getName(),
                tags, cake.getCakeStore().getAddress(), cake.getCakeStore().getLatitude(),
                cake.getCakeStore().getLongitude(), cake.getCakeStore().getInstagramUrl(), saved,
                cakelist);

    }
}
