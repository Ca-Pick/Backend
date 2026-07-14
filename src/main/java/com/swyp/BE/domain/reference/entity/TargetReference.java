package com.swyp.BE.domain.reference.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TargetReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_reference_id")
    private CakeReference cakeReference;

    @Builder
    public TargetReference(String target, CakeReference cakeReference) {

        this.target = target;
        this.cakeReference = cakeReference;
    }

}
