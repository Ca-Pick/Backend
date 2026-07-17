package com.swyp.BE.domain.reference.repository;

import com.swyp.BE.domain.reference.entity.CakeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Query("""
        select c
        from CakeReference c
        join c.targetReferences
        join c.moodReferences
        join c.detailReferences
        where c.id = :referenceId
    """)
    Optional<CakeReference> findDetailById(@Param("referenceId") Long referenceId);

    @Query("""
        select c
        from CakeReference c
        where c.theme = "생일"
    """)
    List<CakeReference> findBirthday();

    @Query("""
        select c
        from CakeReference c
        where c.theme = "기념일"
    """)
    List<CakeReference> findCelebration();

    @Query("""
        select c
        from CakeReference c
        where c.theme = "졸업/입학"
    """)
    List<CakeReference> findAcademic();
}
