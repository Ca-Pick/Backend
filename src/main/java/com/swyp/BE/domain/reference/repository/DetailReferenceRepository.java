package com.swyp.BE.domain.reference.repository;

import com.swyp.BE.domain.reference.entity.DetailReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailReferenceRepository extends JpaRepository<DetailReference, Long> {

    @Query("select distinct d.decoration from DetailReference d")
    List<String> findDistinctDecorations();
}
