package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.PromotionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionTypeRepository extends JpaRepository<PromotionTypeEntity, Integer> {
}
