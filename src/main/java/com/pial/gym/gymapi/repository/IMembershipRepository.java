package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMembershipRepository extends JpaRepository<MembershipEntity, Integer> {
    Optional<MembershipEntity> findByUserClientIdAndCompanyId(Integer clientId, Integer companyId);
}
