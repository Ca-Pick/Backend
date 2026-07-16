package com.swyp.BE.domain.reference.entity;

import com.swyp.BE.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CakeStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instagramUrl;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    private String price;
    private String schedule;

    @OneToMany(mappedBy = "cakeStore")
    private List<CakeReference> cakeReferences;
}
