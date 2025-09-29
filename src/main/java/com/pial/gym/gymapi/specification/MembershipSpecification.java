package com.pial.gym.gymapi.specification;

import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.entity.MembershipEntity;
import com.pial.gym.gymapi.entity.UserEntity;
import jakarta.persistence.criteria.Join;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

public class MembershipSpecification {
    public static Specification<MembershipEntity> findByUserNameRegister(String userNameRegister) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(userNameRegister)) {
                return criteriaBuilder.conjunction();
            }
            Join<MembershipEntity, UserEntity> userJoin = root.join("userRegister");
            return criteriaBuilder.like(userJoin.get("name"), "%" + userNameRegister + "%");
        };
    }

    public static Specification<MembershipEntity> findByUserNameClient(String userNameClient) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(userNameClient)) {
                return criteriaBuilder.conjunction();
            }
            Join<MembershipEntity, UserEntity> userJoin = root.join("userRegister");
            return criteriaBuilder.like(userJoin.get("name"), "%" + userNameClient + "%");
        };
    }

    public static Specification<MembershipEntity> findByCompany(Integer companyId) {
        return (root, query, criteriaBuilder) -> {
            if (companyId == null || companyId == 0) {
                return criteriaBuilder.conjunction();
            }
            Join<MembershipEntity, CompanyEntity> companyJoin = root.join("company");
            return criteriaBuilder.equal(companyJoin.get("id"), companyId);
        };
    }

    public static Specification<MembershipEntity> findByStatus(String status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
