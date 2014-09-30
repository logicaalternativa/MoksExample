package com.logicaalternativa.ejemplomock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logicaalternativa.ejemplomock.model.PromotionCode;

public interface PromotionCodeRepository extends JpaRepository<PromotionCode, String> {

}
