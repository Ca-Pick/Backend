package com.swyp.BE.domain.save.service;

import com.swyp.BE.domain.reference.entity.CakeReference;
import com.swyp.BE.domain.reference.entity.DetailReference;
import com.swyp.BE.domain.reference.repository.ReferenceRepository;
import com.swyp.BE.domain.save.dto.response.SaveListResponse;
import com.swyp.BE.domain.save.entity.CakeSave;
import com.swyp.BE.domain.save.repository.SaveRepository;
import com.swyp.BE.domain.user.repository.UserRepository;
import com.swyp.BE.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaveService {

    private final SaveRepository saveRepository;
    private final ReferenceRepository referenceRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(Long userId, Long referenceId) {

        if (saveRepository.existsByUserIdAndCakeReferenceId(userId, referenceId)) {
            return;
        }

        CakeReference cakeReference = referenceRepository.findById(referenceId)
                .orElseThrow(BusinessException::referenceNotFound);

        CakeSave cakeSave = CakeSave.builder()
                .user(userRepository.getReferenceById(userId))
                .cakeReference(cakeReference)
                .build();

        saveRepository.save(cakeSave);
    }

    @Transactional
    public void cancel(Long userId, Long referenceId) {
        saveRepository.deleteByUserIdAndCakeReferenceId(userId, referenceId);
    }

    public SaveListResponse getMySaves(Long userId) {

        List<CakeSave> cakeSaves = saveRepository.findAllWithCakeReferenceByUserId(userId);

        List<SaveListResponse.SavedCakeInfo> cakes = cakeSaves.stream()
                .map(cakeSave -> {
                    CakeReference cake = cakeSave.getCakeReference();
                    return SaveListResponse.SavedCakeInfo.of(
                            cake.getId(),
                            cake.getInstagramEmbed(),
                            cake.getDetailReferences().stream()
                                    .map(DetailReference::getDecoration)
                                    .toList());
                })
                .toList();

        return SaveListResponse.from(cakes);
    }
}
