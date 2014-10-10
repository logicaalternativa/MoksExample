/*
 *      AddPromotionCodeController.java
 *      
 *      Copyright 2014 Miguel Rafael Esteban Martín (www.logicaalternativa.com) <miguel.esteban@logicaalternativa.com>
 *      
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */
package com.logicaalternativa.ejemplomock.controller.promotioncode;

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
