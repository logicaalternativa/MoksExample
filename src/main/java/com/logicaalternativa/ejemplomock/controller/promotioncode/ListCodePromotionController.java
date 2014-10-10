package com.logicaalternativa.ejemplomock.controller.promotioncode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logicaalternativa.ejemplomock.controller.pojo.ResponseRest;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.PromotionCodeRepository;

@RequestMapping("promotion/code")
@Controller
public class ListCodePromotionController {
	
	@Autowired
	private PromotionCodeRepository promotionCodeRepository;


	@RequestMapping(value = "all", method = {RequestMethod.GET}, produces = "application/json")	
	public @ResponseBody ResponseRest<List<PromotionCode>> getAllPromotionCode() {
		
		List<PromotionCode> listPromotionCodes = getPromotionCodeRepository().findAll();
		
		if ( listPromotionCodes == null ) {
			
			listPromotionCodes = new ArrayList<PromotionCode>();
			
		}
		
		return new ResponseRest<List<PromotionCode>>(ResponseRest.OK, listPromotionCodes );
		
		
	}
	
	public PromotionCodeRepository getPromotionCodeRepository() {
		return promotionCodeRepository;
	}

	public void setPromotionCodeRepository(
			PromotionCodeRepository promotionCodeRepository) {
		this.promotionCodeRepository = promotionCodeRepository;
	}

}
