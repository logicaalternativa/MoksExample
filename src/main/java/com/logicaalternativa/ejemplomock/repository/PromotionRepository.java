package com.logicaalternativa.ejemplomock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logicaalternativa.ejemplomock.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

}
