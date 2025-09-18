package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Column(name = "description_long")
    private String descriptionLong;
    private String logo;
    private Boolean status;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;
}
