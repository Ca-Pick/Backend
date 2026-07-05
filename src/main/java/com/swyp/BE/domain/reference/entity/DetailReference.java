package com.swyp.BE.domain.reference.entity;

import com.swyp.BE.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailReference extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String decoration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_reference_id")
    private CakeReference cakeReference;

    @Builder
    public DetailReference(String decoration, CakeReference cakeReference) {

        this.decoration = decoration;
        this.cakeReference = cakeReference;
    }
}
