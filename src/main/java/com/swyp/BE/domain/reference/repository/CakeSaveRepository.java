package com.swyp.BE.domain.reference.repository;

import com.swyp.BE.domain.save.entity.CakeSave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeSaveRepository extends JpaRepository<CakeSave, Long> {

    boolean existsByUserIdAndCakeReferenceId(Long userId, Long cakeReferenceId);
}
