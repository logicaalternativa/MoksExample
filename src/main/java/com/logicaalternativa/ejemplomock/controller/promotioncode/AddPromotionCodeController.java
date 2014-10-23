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

import java.util.Locale;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.logicaalternativa.ejemplomock.rest.sender.SendMailCodePromotion;

@RequestMapping("/promotion/code")
@Controller
public class AddPromotionCodeController {
	
	protected  static Logger logger = LoggerFactory.getLogger( AddPromotionCodeController.class );
	
	@Autowired
	private AddCodeBusiness addCodeBusiness;

	@Autowired
	private SendMailCodePromotion sendMailCodePromotion;
	
	@RequestMapping(value = "add", method = {RequestMethod.POST}, produces = "application/json", consumes="application/json")		
	public @ResponseBody ResponseRest<String> addCode( @RequestBody final PromotionCode promotionCode, Locale locale ) throws ValidationException, MessagingException{
		
		getAddCodeBusiness().validateAndAdd(  promotionCode );
		
		try {
			
			getSendMailCodePromotion().sendMailCodePromotion(promotionCode.getEmail(), locale );
			
		} catch (Exception e) {
			
			if ( logger.isErrorEnabled() ) {
				
				logger.error("Error send email PromotionCode: " + promotionCode, e );
			}
		}
		
		return new ResponseRest<String>( ResponseRest.OK, "" );
			
		
	}

	public AddCodeBusiness getAddCodeBusiness() {
		return addCodeBusiness;
	}

	public void setAddCodeBusiness(AddCodeBusiness addCodeBusiness) {
		this.addCodeBusiness = addCodeBusiness;
	}

	public SendMailCodePromotion getSendMailCodePromotion() {
		return sendMailCodePromotion;
	}

	public void setSendMailCodePromotion(SendMailCodePromotion sendMailCodePromotion) {
		this.sendMailCodePromotion = sendMailCodePromotion;
	}

}
