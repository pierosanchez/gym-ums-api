package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "promotion_type")
public class PromotionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Column(name = "is_user_relation")
    private Boolean isUserRelation;
    @Column(name = "is_regular")
    private Boolean isRegular;
}
