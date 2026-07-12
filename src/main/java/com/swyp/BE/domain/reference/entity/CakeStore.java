package com.swyp.BE.domain.reference.entity;

import com.swyp.BE.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    @Builder
    public CakeStore(String instagramUrl, String name, String address,
                     BigDecimal latitude, BigDecimal longitude) {

        this.instagramUrl = instagramUrl;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
