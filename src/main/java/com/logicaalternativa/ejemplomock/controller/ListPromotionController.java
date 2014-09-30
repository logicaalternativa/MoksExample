package com.logicaalternativa.ejemplomock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logicaalternativa.ejemplomock.controller.pojo.ResponseRest;
import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.repository.PromotionRepository;

@RequestMapping("/promotion")
@Controller
public class ListPromotionController {
	
	@Autowired
	private PromotionRepository promotionRepository;

	@RequestMapping(value = "all", method = {RequestMethod.GET}, produces = "application/json")		
	public @ResponseBody ResponseRest<List<Promotion>> getAllPromotion(){
		
		final List<Promotion> resultList = getPromotionRepository().findAll();
		
		final ResponseRest<List<Promotion>> responseRest = new ResponseRest<List<Promotion>>();
		responseRest.setCodeResult( ResponseRest.OK );
		responseRest.setResponse(resultList);
		
		return responseRest;
		
	}

	
	public PromotionRepository getPromotionRepository() {
		return promotionRepository;
	}

	public void setPromotionRepository(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

}
