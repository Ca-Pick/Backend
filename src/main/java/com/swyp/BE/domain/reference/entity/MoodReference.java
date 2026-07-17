package com.swyp.BE.domain.reference.entity;

import jakarta.persistence.*;
import lombok.*;

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

}
