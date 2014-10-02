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
