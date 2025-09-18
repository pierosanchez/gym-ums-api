package com.pial.gym.gymapi.specification;

import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.entity.RoleEntity;
import com.pial.gym.gymapi.entity.UserEntity;
import jakarta.persistence.criteria.Join;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecification {
    public static Specification<UserEntity> findByUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(username)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("username"), username);
        };
    }

    public static Specification<UserEntity> findByName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(name)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    public static Specification<UserEntity> findByLasName(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(lastName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("lastName"), lastName);
        };
    }

    public static Specification<UserEntity> findByEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(email)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("email"), email);
        };
    }

    public static Specification<UserEntity> findByGender(String gender) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(gender)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("gender"), gender);
        };
    }

    public static Specification<UserEntity> findByRole(Integer roleId) {
        return (root, query, criteriaBuilder) -> {
            if (roleId == null || roleId == 0) {
                return criteriaBuilder.conjunction();
            }
            Join<UserEntity, RoleEntity> roleJoin = root.join("role");
            return criteriaBuilder.equal(roleJoin.get("id"), roleId);
        };
    }

    public static Specification<UserEntity> findByCompany(Integer companyId) {
        return (root, query, criteriaBuilder) -> {
            if (companyId == null || companyId == 0) {
                return criteriaBuilder.conjunction();
            }
            Join<UserEntity, CompanyEntity> companyJoin = root.join("company");
            return criteriaBuilder.equal(companyJoin.get("id"), companyId);
        };
    }

    public static Specification<UserEntity> findByStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<UserEntity> findByCreationDate(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> {
            if (fromDate == null || toDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("creationDate"), fromDate, toDate);
        };
    }

    public static Specification<UserEntity> findByModificationDate(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> {
            if (fromDate == null || toDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("modificationDate"), fromDate, toDate);
        };
    }
}
