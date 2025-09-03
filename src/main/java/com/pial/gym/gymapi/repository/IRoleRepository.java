package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
}
