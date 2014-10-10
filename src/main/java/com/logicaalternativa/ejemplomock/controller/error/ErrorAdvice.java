/*
 *      ErrorAdvice.java
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
package com.logicaalternativa.ejemplomock.controller.error;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logicaalternativa.ejemplomock.controller.pojo.ResponseRest;
import com.logicaalternativa.ejemplomock.exception.ValidationException;

@ControllerAdvice
public class ErrorAdvice {
	
	@Autowired
	private MessageSource messageSource;
	
	protected  static Logger logger = LoggerFactory.getLogger( ErrorAdvice.class );
	
	@ExceptionHandler(Exception.class)
   
    public @ResponseBody ResponseRest<String> handleAllException( Exception ex, HttpServletRequest request,  Locale locale ) {
    	
    	if ( logger.isErrorEnabled() ) {
    		
    		logger.error("handleAllException Error:" +  ex.getMessage(), ex );
    		
    	}
    	
    	final ResponseRest<String> responseError = new ResponseRest<String>();
   
    	if ( ex instanceof ValidationException ) {
    		
    		final String messageException =  ex.getMessage();
    		
    		final String messageI18n = getMessageSource().getMessage(messageException , null, messageException, locale );
    		
    		responseError.setCodeResult( ResponseRest.VALIDATION_ERROR );
    		responseError.setResponse( messageI18n );
    		
    	} else {
    		
    		responseError.setCodeResult( ResponseRest.GENERIC_ERROR );
    		responseError.setResponse( "" );
    		
    	}
    	
    	return responseError;
 
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	

}
