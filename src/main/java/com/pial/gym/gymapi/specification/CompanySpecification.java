package com.pial.gym.gymapi.specification;

import com.pial.gym.gymapi.entity.CompanyEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {
    public static Specification<CompanyEntity> findByDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(description)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("description"), "%" + description + "%");
        };
    }

    public static Specification<CompanyEntity> findByDescriptionLong(String descriptionLong) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(descriptionLong)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("descriptionLong"), "%" + descriptionLong + "%");
        };
    }

    public static Specification<CompanyEntity> findByStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
