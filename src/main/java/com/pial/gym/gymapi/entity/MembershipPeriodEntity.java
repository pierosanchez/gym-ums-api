package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "membership_period")
public class MembershipPeriodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_membership")
    private MembershipEntity membership;
    @OneToOne
    @JoinColumn(name = "id_promotion")
    private PromotionEntity promotion;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private LocalDate startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;
}
