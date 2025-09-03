package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<PermissionEntity, Integer> {
}
