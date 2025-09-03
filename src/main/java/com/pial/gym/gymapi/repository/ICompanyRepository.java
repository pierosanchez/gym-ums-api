package com.pial.gym.gymapi.repository;

import com.pial.gym.gymapi.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<CompanyEntity, Integer>, JpaSpecificationExecutor<CompanyEntity> {
}
