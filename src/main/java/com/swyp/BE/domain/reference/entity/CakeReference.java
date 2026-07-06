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
public class CakeReference extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instagramEmbed;
    private String price;
    private String orderDate;
    private String place;
    private String shape;
    private String color;
    private String mood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_store_id")
    private CakeStore cakeStore;

    @Builder
    public CakeReference(String instagramEmbed,
                         String price, String orderDate, String place,
                         String shape, String color, String mood,
                         CakeStore cakeStore) {

        this.instagramEmbed = instagramEmbed;
        this.price = price;
        this.orderDate = orderDate;
        this.place = place;
        this.shape = shape;
        this.color = color;
        this.mood = mood;
        this.cakeStore = cakeStore;
    }
}
