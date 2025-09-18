package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String code;
    private Boolean visible;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;
}
