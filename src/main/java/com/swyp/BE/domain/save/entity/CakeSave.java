package com.swyp.BE.domain.save.entity;


import com.swyp.BE.domain.reference.entity.CakeReference;
import com.swyp.BE.domain.user.entity.User;
import com.swyp.BE.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CakeSave extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_reference_id", nullable = false)
    private CakeReference cakeReference;

    @Builder
    public CakeSave(User user, CakeReference cakeReference) {

        this.user = user;
        this.cakeReference = cakeReference;
    }
}
