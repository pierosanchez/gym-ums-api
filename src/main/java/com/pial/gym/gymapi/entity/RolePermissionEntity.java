package com.pial.gym.gymapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
    @OneToOne
    @JoinColumn(name = "id_permission")
    private PermissionEntity permission;
    private Boolean status;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private Date modificationDate;
}
