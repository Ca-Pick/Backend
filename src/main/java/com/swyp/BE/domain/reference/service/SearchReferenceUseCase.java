package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.request.SearchRequest;
import com.swyp.BE.domain.reference.dto.response.SearchResponse;
import com.swyp.BE.domain.reference.entity.CakeReference;
import com.swyp.BE.domain.reference.entity.DetailReference;
import com.swyp.BE.domain.reference.repository.CakeSaveRepository;
import com.swyp.BE.domain.reference.repository.ReferenceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchReferenceUseCase {

    private final ReferenceRepository referenceRepository;
    private final CakeSaveRepository cakeSaveRepository;

    public SearchResponse excute(Long userId, SearchRequest request) {

        List<CakeReference> byCake = referenceRepository.findByCake(
                request.getPlace(),
                request.getTarget(),
                request.getShape(),
                request.getColor(),
                request.getMood(),
                request.getDetailTags());

        Collections.shuffle(byCake);

        List<SearchResponse.InstagramEmbedInfo> cakes = byCake.stream()
                .map(cake -> {
                    boolean saved = false;
                    if (userId != null) {
                        saved = cakeSaveRepository.existsByUserIdAndCakeReferenceId(userId, cake.getId());
                    }

                    List<String> detailTags = cake.getDetailReferences().stream()
                            .map(DetailReference::getDecoration)
                            .filter(request.getDetailTags()::contains)
                            .toList();
                    int detailCount = detailTags.size();

                    return SearchResponse.InstagramEmbedInfo.of(
                            cake.getId(), cake.getInstagramEmbed(), saved, detailTags, detailCount);
                }).sorted(Comparator.comparingInt(SearchResponse.InstagramEmbedInfo::getCakeDetailCount)
                        .reversed()).toList();

        List<String> tags = new ArrayList<>();
        tags.add(request.getPlace());
        tags.add(request.getTarget());
        tags.add(request.getShape());
        tags.add(request.getColor());
        tags.add(request.getMood());
        tags.addAll(request.getDetailTags());


        return SearchResponse.from(tags, cakes);
    }
}
