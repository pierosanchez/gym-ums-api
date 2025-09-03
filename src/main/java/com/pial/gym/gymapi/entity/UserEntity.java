package com.pial.gym.gymapi.entity;

import com.pial.gym.gymapi.enumerable.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @OneToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
    @OneToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;
    private Boolean status;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private Date modificationDate;
}
