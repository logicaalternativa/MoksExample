/*
 *      SendMailCodePromotionImp.java
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
package com.logicaalternativa.ejemplomock.rest.sender;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.logicaalternativa.ejemplomock.exception.ValidationException;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.PromotionCodeRepository;


@Component
public class SendMailCodePromotionImp implements SendMailCodePromotion {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private PromotionCodeRepository promotionCodeRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Value(value="${mail.sender.from}")
	private String from;
	
	@Override
	public void sendMailCodePromotion(String  email, Locale locale) throws ValidationException, MessagingException {
		
		if ( email == null  ) {
			
			throw new ValidationException( "validation.error.email.null" );
			
		}
		
		if ( locale == null ) {
			
			throw new ValidationException( "validation.error.locale.null" );
			
		}
		
		final PromotionCode promotionCode = getPromotionCodeRepository().findOne( email );
		
		if ( promotionCode == null ) {
			
			throw new ValidationException( "validation.error.promotionCode.notbbdd" );
			
		}
		
		sendEmail( promotionCode , locale );
				
	}

	private void sendEmail( final PromotionCode promotionCode, final Locale locale ) throws MessagingException{
		
		MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		final String subject = getMessageSource()
									.getMessage(  "email.promotionCode.subject" , 
												   new Object[]{},
												   "email.promotionCode.subject", 
												   locale 
												);
		
		final String to  = ( new StringBuilder() )
							.append( promotionCode.getNameUser() != null ? promotionCode.getNameUser().toUpperCase() : "" )
							.append( "<" )
							.append( promotionCode.getEmail() != null ? promotionCode.getEmail() : "" )
							.append( ">" )
							.toString();
		
		final String text = getMessageSource()
									.getMessage( "email.promotionCode.txt" , 
											  	 new Object[]{ promotionCode.getCode() },
											     "email.promotionCode.txt", 
											     locale 
											);
		
		final String html = getMessageSource()
									.getMessage( "email.promotionCode.html" , 
											  	 new Object[]{ promotionCode.getCode() },
											     "email.promotionCode.html", 
											     locale 
											);
		
		helper.setFrom( getFrom() );
		helper.setTo( to );
		helper.setSubject( subject );
		helper.setText( text , html );		 
		
	    getJavaMailSender().send( mimeMessage );
		
	}
	
	
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public PromotionCodeRepository getPromotionCodeRepository() {
		return promotionCodeRepository;
	}


	public void setPromotionCodeRepository(
			PromotionCodeRepository promotionCodeRepository) {
		this.promotionCodeRepository = promotionCodeRepository;
	}


	public MessageSource getMessageSource() {
		return messageSource;
	}


	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
