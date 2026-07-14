package com.swyp.BE.domain.reference.service;


import com.swyp.BE.domain.reference.dto.request.SearchRequest;
import com.swyp.BE.domain.reference.dto.response.SearchResponse;
import com.swyp.BE.domain.reference.entity.CakeReference;
import com.swyp.BE.domain.reference.entity.DetailReference;
import com.swyp.BE.domain.reference.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchReferenceUseCase {

    private final ReferenceRepository referenceRepository;

    public SearchResponse excute(SearchRequest request) {

        List<CakeReference> byCake = referenceRepository.findByCake(
                request.getPlace(),
                request.getTarget(),
                request.getShape(),
                request.getColor(),
                request.getMood(),
                request.getDetailTags());


        List<SearchResponse.InstagramEmbedInfo> cakes = byCake.stream()
                .map(cake -> SearchResponse.InstagramEmbedInfo.of(
                        cake.getInstagramEmbed(),
                        cake.getDetailReferences().stream()
                                .map(DetailReference::getDecoration)
                                    .toList()))
                .toList();


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
