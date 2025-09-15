package com.pial.gym.gymapi.utils;

import com.pial.gym.gymapi.dto.model.*;
import com.pial.gym.gymapi.entity.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ModelConvertionUtils {

    public static User getUser(UserEntity userEntity) {
        User user = new User();
        if (Objects.nonNull(userEntity)) {
            Role role = getRole(userEntity.getRole());
            Company company = getCompany(userEntity.getCompany());
            user.setId(userEntity.getId());
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setName(userEntity.getName());
            user.setLasName(userEntity.getLastName());
            user.setEmail(userEntity.getEmail());
            user.setPhone(userEntity.getPhone());
            user.setGender(userEntity.getGender().toString());
            user.setRole(role);
            user.setCompany(company);
            user.setStatus(userEntity.getStatus());
            user.setCreationDate(userEntity.getCreationDate());
            user.setModificationDate(userEntity.getModificationDate());
        }
        return user;
    }

    public static Role getRole(RoleEntity roleEntity) {
        Role role = new Role();
        if (Objects.nonNull(roleEntity)) {
            role.setId(roleEntity.getId());
            role.setDescription(roleEntity.getDescription());
            role.setCode(roleEntity.getCode());
            role.setVisible(roleEntity.getVisible());
            role.setCreationDate(roleEntity.getCreationDate());
            role.setModificationDate(roleEntity.getModificationDate());
        }
        return role;
    }

    public static Company getCompany(CompanyEntity companyEntity) {
        Company company = new Company();
        if (Objects.nonNull(companyEntity)) {
            company.setId(companyEntity.getId());
            company.setDescription(companyEntity.getDescription());
            company.setDescriptionLong(companyEntity.getDescriptionLong());
            company.setLogo(companyEntity.getLogo());
            company.setStatus(companyEntity.getStatus());
            company.setCreationDate(companyEntity.getCreationDate());
            company.setModificationDate(companyEntity.getModificationDate());
        }
        return company;
    }

    public static Permission getPermission(PermissionEntity permissionEntity) {
        Permission permission = new Permission();
        if (Objects.nonNull(permissionEntity)) {
            permission.setId(permissionEntity.getId());
            permission.setDescription(permissionEntity.getDescription());
            permission.setCode(permissionEntity.getCode());
            permission.setVisible(permissionEntity.getVisible());
            permission.setCreationDate(permissionEntity.getCreationDate());
            permission.setModificationDate(permissionEntity.getModificationDate());
        }
        return permission;
    }

    public static RolePermission getRolePermission(RolePermissionEntity rolePermissionEntity) {
        RolePermission rolePermission = new RolePermission();
        if (Objects.nonNull(rolePermissionEntity)) {
            Role role = getRole(rolePermissionEntity.getRole());
            Permission permission = getPermission(rolePermissionEntity.getPermission());
            rolePermission.setId(rolePermissionEntity.getId());
            rolePermission.setRole(role);
            rolePermission.setPermission(permission);
            rolePermission.setStatus(rolePermissionEntity.getStatus());
            rolePermission.setCreationDate(rolePermissionEntity.getCreationDate());
            rolePermission.setModificationDate(rolePermissionEntity.getModificationDate());
        }
        return rolePermission;
    }

    public static Promotion getPromotion(PromotionEntity promotionEntity) {
        Promotion promotion = new Promotion();
        if (Objects.nonNull(promotionEntity)) {
            PromotionType promotionType = getPromotionType(promotionEntity.getPromotionType());
            Company company = getCompany(promotionEntity.getCompany());
            promotion.setId(promotionEntity.getId());
            promotion.setTitle(promotionEntity.getTitle());
            promotion.setDuration(promotionEntity.getDuration());
            promotion.setDurationType(promotionEntity.getDurationType());
            promotion.setPromotionType(promotionType);
            promotion.setCompany(company);
            promotion.setStatus(promotionEntity.getStatus());
            promotion.setPrice(promotionEntity.getPrice());
            promotion.setStartDate(promotionEntity.getStartDate());
            promotion.setEndDate(promotionEntity.getEndDate());
            promotion.setCreationDate(promotionEntity.getCreationDate());
            promotion.setModificationDate(promotionEntity.getModificationDate());
        }
        return promotion;
    }

    public static PromotionType getPromotionType(PromotionTypeEntity promotionTypeEntity) {
        PromotionType promotionType = new PromotionType();
        if (Objects.nonNull(promotionTypeEntity)) {
            promotionType.setId(promotionTypeEntity.getId());
            promotionType.setDescription(promotionTypeEntity.getDescription());
            promotionType.setIsUserRelation(promotionTypeEntity.getIsUserRelation());
            promotionType.setIsRegular(promotionTypeEntity.getIsRegular());
        }
        return promotionType;
    }
}
