package com.pial.gym.gymapi.entity;

import com.pial.gym.gymapi.enumerable.PromotionDurationTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modificaion_date")
    private Date modificationDate;
}
