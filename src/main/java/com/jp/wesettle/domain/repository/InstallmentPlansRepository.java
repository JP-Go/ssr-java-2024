package com.jp.wesettle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.wesettle.domain.model.InstallmentPlan;

/**
 * InstallmentPlansRepository
 */
public interface InstallmentPlansRepository extends JpaRepository<InstallmentPlan,Long>{

    
}
