package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionRepository extends JpaRepository<PromotionEntity, Integer> {
}
