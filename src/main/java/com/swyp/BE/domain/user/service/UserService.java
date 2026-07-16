package com.swyp.BE.domain.user.service;

import com.swyp.BE.domain.save.repository.SaveRepository;
import com.swyp.BE.domain.user.dto.response.UserInfoResponse;
import com.swyp.BE.domain.user.entity.User;
import com.swyp.BE.domain.user.repository.UserRepository;
import com.swyp.BE.global.exception.BusinessException;
import com.swyp.BE.global.exception.ErrorCode;
import com.swyp.BE.global.jwt.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SaveRepository saveRepository;

    @Transactional(readOnly = true)
    public UserInfoResponse getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        return UserInfoResponse.from(user);
    }

    @Transactional
    public void updateNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        if (!user.getNickname().equals(newNickname)
                && userRepository.existsByNickname(newNickname)) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        }

        user.updateNickname(newNickname);
    }

    @Transactional
    public void withdraw(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        saveRepository.deleteAllByUserId(userId);
        refreshTokenRepository.deleteByUserId(userId);
        userRepository.delete(user);
    }
}
