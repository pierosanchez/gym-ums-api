package com.pial.gym.gymapi.entity;

import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "promotion")
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(name = "duration_type")
    private PromotionDurationTypeEnum durationType;
    @OneToOne
    @JoinColumn(name = "id_promotion_type")
    private PromotionTypeEntity promotionType;
    @OneToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;
    private Boolean status;
    private BigDecimal price;
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
