package com.logicaalternativa.ejemplomock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.repository.PromotionRepository;

@RequestMapping("/test")
@Controller
public class TestController {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	
	@RequestMapping(value = "test1", method = {RequestMethod.GET}, produces = "application/json")		
	public @ResponseBody Promotion test1( ) {
		
		return getPromotionRepository().findOne( 1 );		
		
	}
	
	
	public PromotionRepository getPromotionRepository() {
		return promotionRepository;
	}

	public void setPromotionRepository(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	

}
