package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.MembershipUserRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMembershipUserRelationRepository extends JpaRepository<MembershipUserRelationEntity, Integer> {
}
