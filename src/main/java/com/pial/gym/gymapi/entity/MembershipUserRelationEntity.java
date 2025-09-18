package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "membership_user_relation")
public class MembershipUserRelationEntity {
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
