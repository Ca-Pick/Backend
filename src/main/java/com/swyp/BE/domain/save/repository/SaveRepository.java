package com.swyp.BE.domain.save.repository;

import com.swyp.BE.domain.save.entity.CakeSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaveRepository extends JpaRepository<CakeSave, Long> {
    void deleteAllByUserId(Long userId);

    boolean existsByUserIdAndCakeReferenceId(Long userId, Long cakeReferenceId);

    void deleteByUserIdAndCakeReferenceId(Long userId, Long cakeReferenceId);

    @Query("""
        select cs
        from CakeSave cs
        join fetch cs.cakeReference c
        left join fetch c.detailReferences
        where cs.user.id = :userId
        order by cs.id desc
    """)
    List<CakeSave> findAllWithCakeReferenceByUserId(@Param("userId") Long userId);
}