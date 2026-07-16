package com.swyp.BE.domain.reference.entity;


import com.swyp.BE.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CakeReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instagramEmbed;
    private String place;
    private String shape;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_store_id")
    private CakeStore cakeStore;

    @OneToMany(mappedBy = "cakeReference")
    private List<DetailReference> detailReferences;

    @OneToMany(mappedBy = "cakeReference")
    private List<MoodReference> moodReferences;

    @OneToMany(mappedBy = "cakeReference")
    private List<TargetReference> targetReferences;

}
