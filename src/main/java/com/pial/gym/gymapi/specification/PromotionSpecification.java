package com.pial.gym.gymapi.specification;

import com.pial.gym.gymapi.entity.CompanyEntity;
import com.pial.gym.gymapi.entity.PromotionEntity;
import com.pial.gym.gymapi.entity.PromotionTypeEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PromotionSpecification {
    public static Specification<PromotionEntity> findByTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(title)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<PromotionEntity> findByDuration(Integer min, Integer max) {
        return (root, query, criteriaBuilder) -> {
            if ((min == null || min == 0) || (max == null || max == 0)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("duration"), min, max);
        };
    }

    public static Specification<PromotionEntity> findByDurationType(String promotionDurationType) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(promotionDurationType)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("promotionDurationType"), promotionDurationType);
        };
    }

    public static Specification<PromotionEntity> findByPromotionType(Integer promotionTypeId) {
        return (root, query, criteriaBuilder) -> {
            if (promotionTypeId == null || promotionTypeId == 0) {
                return criteriaBuilder.conjunction();
            }
            Join<PromotionEntity, PromotionTypeEntity> promotionTypeEntityJoin = root.join("promotionType");
            return criteriaBuilder.equal(promotionTypeEntityJoin.get("id"), promotionTypeId);
        };
    }

    public static Specification<PromotionEntity> findByCompany(Integer companyId) {
        return (root, query, criteriaBuilder) -> {
            if (companyId == null || companyId == 0) {
                return criteriaBuilder.conjunction();
            }
            Join<PromotionEntity, CompanyEntity> companyEntityJoin = root.join("company");
            return criteriaBuilder.equal(companyEntityJoin.get("id"), companyId);
        };
    }

    public static Specification<PromotionEntity> findByStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<PromotionEntity> findByStartAndEndDate(LocalDate periodStart, LocalDate periodEnd) {
        return (root, query, criteriaBuilder) -> {
            if (periodStart == null || periodEnd == null) {
                return criteriaBuilder.conjunction();
            }
            Predicate predicate1 = criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), periodStart);
            Predicate predicate2 = criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), periodEnd);
            return criteriaBuilder.and(predicate1, predicate2);
        };
    }
}
