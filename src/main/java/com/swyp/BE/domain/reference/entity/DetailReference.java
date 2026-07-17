package com.swyp.BE.domain.reference.entity;

import com.swyp.BE.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String decoration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_reference_id")
    private CakeReference cakeReference;

}
