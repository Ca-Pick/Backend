package com.swyp.BE.domain.save.repository;

import com.swyp.BE.domain.save.entity.CakeSave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveRepository extends JpaRepository<CakeSave, Long> {
    void deleteAllByUserId(Long userId);
}