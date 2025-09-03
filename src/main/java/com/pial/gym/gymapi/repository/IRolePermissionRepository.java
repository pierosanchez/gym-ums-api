package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.RolePermissionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRolePermissionRepository extends JpaRepository<RolePermissionEntity, Integer> {
    Slice<RolePermissionEntity> findAllByRoleId(Integer id, Pageable pageable);
    Slice<RolePermissionEntity> findAllByPermissionId(Integer id, Pageable pageable);

    List<RolePermissionEntity> findAllByRoleId(Integer id);
}
