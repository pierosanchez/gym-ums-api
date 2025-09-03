package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "promotion_user_relation")
public class PromotionUserRelationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_membership_period")
    private MembershipPeriodEntity membershipPeriod;
    @OneToOne
    @JoinColumn(name = "id_user_client")
    private UserEntity userClient;
}
