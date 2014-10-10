/*
 *      ListPromotionController.java
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
package com.logicaalternativa.ejemplomock.controller.promotion;

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
