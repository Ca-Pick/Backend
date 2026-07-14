package com.swyp.BE.domain.reference.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoodReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_reference_id")
    private CakeReference cakeReference;

    @Builder
    public MoodReference(String mood, CakeReference cakeReference) {

        this.mood = mood;
        this.cakeReference = cakeReference;
    }
}
