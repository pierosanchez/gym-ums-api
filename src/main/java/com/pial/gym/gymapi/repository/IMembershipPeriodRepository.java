package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.MembershipPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMembershipPeriodRepository extends JpaRepository<MembershipPeriodEntity, Integer> {
}
