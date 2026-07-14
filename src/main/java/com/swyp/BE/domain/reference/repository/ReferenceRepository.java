package com.swyp.BE.domain.reference.repository;

import com.swyp.BE.domain.reference.entity.CakeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<CakeReference, Long> {



    @Query("""
        select c
        from CakeReference c
        join c.targetReferences t
        join c.moodReferences m
        join c.detailReferences d
        where c.place = :place
        and t.target = :target
        and c.shape = :shape
        and c.color = :color
        and m.mood = :mood
        and d.decoration in :detailTags
    """)
    List<CakeReference> findByCake(
            @Param("place") String place,
            @Param("target") String target,
            @Param("shape") String shape,
            @Param("color") String color,
            @Param("mood") String mood,
            @Param("detailTags") List<String> detailTags);


}
