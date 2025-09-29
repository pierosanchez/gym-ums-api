package com.pial.gym.gymapi.dto.model;

import lombok.Data;

@Data
public class MembershipUserRelation {
    private Integer id;
    private MembershipPeriod membershipPeriod;
    private User userClient;
}
