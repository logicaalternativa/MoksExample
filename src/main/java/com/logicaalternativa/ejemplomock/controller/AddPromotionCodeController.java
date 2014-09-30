package com.logicaalternativa.ejemplomock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logicaalternativa.ejemplomock.controller.pojo.ResponseRest;
import com.logicaalternativa.ejemplomock.exception.ValidationException;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.rest.bussiness.AddCodeBusiness;

@RequestMapping("/promotion/code")
@Controller
public class AddPromotionCodeController {
	
	@Autowired
	private AddCodeBusiness addCodeBusiness;
	
	@RequestMapping(value = "add", method = {RequestMethod.POST}, produces = "application/json", consumes="application/json")		
	public @ResponseBody ResponseRest<String> addCode( @RequestBody final PromotionCode promotionCode ) throws ValidationException{
		
		getAddCodeBusiness().validateAndAdd(  promotionCode );
		
		return new ResponseRest<String>( ResponseRest.OK, "" );
			
		
	}

	public AddCodeBusiness getAddCodeBusiness() {
		return addCodeBusiness;
	}

	public void setAddCodeBusiness(AddCodeBusiness addCodeBusiness) {
		this.addCodeBusiness = addCodeBusiness;
	}

}
