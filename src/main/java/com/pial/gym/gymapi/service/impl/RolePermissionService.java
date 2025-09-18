package com.pial.gym.gymapi.service.impl;

import com.pial.gym.gymapi.dto.model.Permission;
import com.pial.gym.gymapi.dto.model.Role;
import com.pial.gym.gymapi.dto.request.RolePermissionGetAllByFilterRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionCreateRequest;
import com.pial.gym.gymapi.dto.request.RolePermissionUpdateRequest;
import com.pial.gym.gymapi.dto.response.RolePermissionDetailResponse;
import com.pial.gym.gymapi.dto.response.RolePermissionGetAllResponse;
import com.pial.gym.gymapi.entity.PermissionEntity;
import com.pial.gym.gymapi.entity.RoleEntity;
import com.pial.gym.gymapi.entity.RolePermissionEntity;
import com.pial.gym.gymapi.repository.IPermissionRepository;
import com.pial.gym.gymapi.repository.IRoleRepository;
import com.pial.gym.gymapi.repository.IUserRepository;
import com.pial.gym.gymapi.repository.IRolePermissionRepository;
import com.pial.gym.gymapi.service.IRolePermissionService;
import com.pial.gym.gymapi.utils.ModelConvertionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class RolePermissionService implements IRolePermissionService {
    @Autowired
    private IRolePermissionRepository iRolePermissionRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private IPermissionRepository iPermissionRepository;
    @Autowired
    private MessageSource messageSource;

    @Override
    public String create(RolePermissionCreateRequest request) {
        String message = messageSource.getMessage("msg.role-permission.creation.success", null, LocaleContextHolder.getLocale());
        try {
            RoleEntity roleEntity = iRoleRepository.findById(request.getRoleId()).orElseThrow(() -> new Exception(messageSource.getMessage(
                    "msg.role.find.notfound", null, LocaleContextHolder.getLocale())));
            List<PermissionEntity> listPermissionEntity = iPermissionRepository.findAllById(request.getListPermissionId());
            List<RolePermissionEntity> rolePermissionEntityList = new ArrayList<>();
            listPermissionEntity.forEach(permissionEntity -> {
                RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                rolePermissionEntity.setRole(roleEntity);
                rolePermissionEntity.setPermission(permissionEntity);
                rolePermissionEntity.setStatus(true);
                rolePermissionEntity.setCreationDate(LocalDate.now());
                rolePermissionEntity.setModificationDate(LocalDate.now());
                rolePermissionEntityList.add(rolePermissionEntity);
            });
            iRolePermissionRepository.saveAllAndFlush(rolePermissionEntityList);
        } catch (Exception e) {
            log.error("Error create user role permission: {}", e.getMessage());
            message = messageSource.getMessage("msg.role-permission.creation.error", null, LocaleContextHolder.getLocale());
        }
        return message;
    }

    @Override
    public RolePermissionDetailResponse getDetail(Integer roleId) {
        RolePermissionDetailResponse rolePermissionDetailResponse = new RolePermissionDetailResponse();
        List<RolePermissionEntity> rolePermissionEntityList = iRolePermissionRepository.findAllByRoleId(roleId);
        Role role = ModelConvertionUtils.getRole(rolePermissionEntityList.stream().findFirst().orElseThrow().getRole());

        List<Permission> permissionList = new ArrayList<>();
        rolePermissionEntityList.forEach(rolePermissionEntity -> {
            if (permissionList.stream().noneMatch(permissionEntity -> Objects.equals(permissionEntity.getId(), rolePermissionEntity.getPermission().getId()))) {
                permissionList.add(ModelConvertionUtils.getPermission(rolePermissionEntity.getPermission()));
            }
        });

        rolePermissionDetailResponse.setRole(role);
        rolePermissionDetailResponse.setPermissionList(permissionList);
        return rolePermissionDetailResponse;
    }

    @Override
    public String update(RolePermissionUpdateRequest request) {
        String message = messageSource.getMessage("msg.role-permission.update.success", null, LocaleContextHolder.getLocale());
        try {
            List<RolePermissionEntity> rolePermissionEntityCreateList = new ArrayList<>();
            List<RolePermissionEntity> rolePermissionEntityList = iRolePermissionRepository.findAllByRoleId(request.getRoleId());

            // Update status to false
            rolePermissionEntityList.forEach(rolePermissionEntity -> {
                if (request.getListPermissionId().stream().noneMatch(permissionId -> Objects.equals(rolePermissionEntity.getPermission().getId(), permissionId))) {
                    rolePermissionEntity.setStatus(false);
                } else if (request.getListPermissionId().stream().anyMatch(permissionId -> Objects.equals(rolePermissionEntity.getPermission().getId(), permissionId))) {
                    rolePermissionEntity.setStatus(true);
                }
            });
            if (!rolePermissionEntityList.isEmpty()) {
                iRolePermissionRepository.saveAllAndFlush(rolePermissionEntityList);
            }

            // Create new permission
            for (Integer permissionId : request.getListPermissionId()) {
                if (rolePermissionEntityList.stream().noneMatch(rolePermissionEntity ->
                        Objects.equals(rolePermissionEntity.getPermission().getId(), permissionId))) {
                    // should create a new register
                    PermissionEntity permissionEntity = iPermissionRepository.findById(permissionId).orElseThrow(() -> new Exception("Permission not found"));
                    RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                    rolePermissionEntity.setRole(rolePermissionEntityList.getFirst().getRole());
                    rolePermissionEntity.setPermission(permissionEntity);
                    rolePermissionEntity.setStatus(true);
                    rolePermissionEntity.setCreationDate(LocalDate.now());
                    rolePermissionEntity.setModificationDate(LocalDate.now());
                    rolePermissionEntityCreateList.add(rolePermissionEntity);
                }
            }
            if (!rolePermissionEntityCreateList.isEmpty()) {
                iRolePermissionRepository.saveAllAndFlush(rolePermissionEntityCreateList);
            }
        } catch (Exception e) {
            log.error("There was an error while updating: {}", e.getMessage());
            message = messageSource.getMessage("msg.role-permission.update.error", null, LocaleContextHolder.getLocale());
        }

        return message;
    }

    @Override
    public Slice<RolePermissionGetAllResponse> getAllByFilter(RolePermissionGetAllByFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Slice<RolePermissionEntity> rolePermissionEntitySlice = iRolePermissionRepository.findAll(pageable);
        List<RolePermissionGetAllResponse> rolePermissionGetAllResponseList = new ArrayList<>();
        for (RolePermissionEntity rolePermissionEntity : rolePermissionEntitySlice) {
            int permissionsQuantity = 0;
            RolePermissionGetAllResponse obj = new RolePermissionGetAllResponse();
            obj.setRole(ModelConvertionUtils.getRole(rolePermissionEntity.getRole()));

            for (RolePermissionEntity rolePermissionEntity1 : rolePermissionEntitySlice) {
                if (Objects.equals(rolePermissionEntity1.getRole().getId(), rolePermissionEntity.getRole().getId())) {
                    permissionsQuantity++;
                }
            }
            obj.setPermissionsQuantity(permissionsQuantity);

            if (rolePermissionGetAllResponseList.stream().noneMatch(rolePermissionGetAllResponse -> Objects.equals(rolePermissionGetAllResponse.getRole().getId(), rolePermissionEntity.getRole().getId()))) {
                rolePermissionGetAllResponseList.add(obj);
            }
        }

        boolean hasNext = rolePermissionGetAllResponseList.size() > pageable.getPageSize();
        List<RolePermissionGetAllResponse> sliceContent = hasNext ? rolePermissionGetAllResponseList.subList(0, pageable.getPageSize()) : rolePermissionGetAllResponseList;
        return new SliceImpl<>(sliceContent, pageable, hasNext);
    }
}
