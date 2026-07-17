package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.response.DetailResponse;
import com.swyp.BE.domain.reference.entity.*;
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

    public DetailResponse excute(Long referenceId) {

        CakeReference cake = referenceRepository.findDetailById(referenceId)
                .orElseThrow(BusinessException::referenceNotFound);

        CakeStore cakeStore = cakeStoreRepository.findById(cake.getCakeStore().getId())
                .orElseThrow(BusinessException::referenceNotFound);

        List<DetailResponse.CakeListInfo> cakelist = cakeStore.getCakeReferences().stream()
                .map(c -> DetailResponse.CakeListInfo.of(
                        c.getId(),
                        c.getInstagramEmbed()))
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

        return DetailResponse.from(cake.getInstagramEmbed(), cake.getCakeStore().getName(),
                tags, cake.getCakeStore().getPrice(), cake.getCakeStore().getSchedule() ,
                cake.getCakeStore().getAddress(), cake.getCakeStore().getLatitude(),
                cake.getCakeStore().getLongitude(), cake.getCakeStore().getInstagramUrl(),
                cakelist);

    }
}
