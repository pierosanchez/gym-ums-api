package com.pial.gym.gymapi.entity;

import com.pial.gym.gymapi.enumerable.MembershipStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "membership")
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_user_register")
    private UserEntity userRegister;
    @OneToOne
    @JoinColumn(name = "id_user_client")
    private UserEntity userClient;
    @OneToOne
    @JoinColumn(name = "id_company")
    private CompanyEntity company;
    @Enumerated(EnumType.STRING)
    private MembershipStatusEnum status;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private Date modificationDate;
}
