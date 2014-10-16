package com.logicaalternativa.ejemplomock.rest.sender;

import java.util.Locale;

import javax.mail.MessagingException;

import com.logicaalternativa.ejemplomock.exception.ValidationException;

public interface SendMailCodePromotion {

	public abstract void sendMailCodePromotion(String  email, Locale locale)
			throws ValidationException, MessagingException;

	
	

}