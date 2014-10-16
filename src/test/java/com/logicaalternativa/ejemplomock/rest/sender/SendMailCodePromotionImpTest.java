/*
 *      SendMailCodePromotionImpTest.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.mock.MessageSourceMock;
import org.springframework.mail.javamail.mock.JavaMailServerMock;

import com.logicaalternativa.ejemplomock.exception.ValidationException;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.mock.PromotionCodeRepositoryMock;
import com.logicaalternativa.ejemplomock.rest.bussiness.AddCodeBusinessImpTest;

public class SendMailCodePromotionImpTest {
	
	protected  static Logger logger = LoggerFactory.getLogger( AddCodeBusinessImpTest.class );
	
	private SendMailCodePromotionImp sendMailCodePromotionImp;
	
	private PromotionCodeRepositoryMock promotionCodeRepositoryMock;	
	
	private JavaMailServerMock javaMailServerMock;
	
	private MessageSourceMock messageSourceMock;
	
	private PromotionCode promotionCode;
	
	private String email;
	
	private String nameUser;
	
	private Locale locale;

	@Before
	public void setUp() throws Exception {
		
		email = "test@test.dum";
		
		nameUser = "User Name";
		
		locale = new Locale( "es" );
		
		promotionCode = new PromotionCode();
		promotionCode.setEmail( email );
		promotionCode.setNameUser( nameUser );
		promotionCode.setCode( "aaa" );
		
		promotionCodeRepositoryMock = new PromotionCodeRepositoryMock();
		promotionCodeRepositoryMock.setPromotionCode( promotionCode );
		
		javaMailServerMock = new JavaMailServerMock();
		
		messageSourceMock = new MessageSourceMock();
		
		sendMailCodePromotionImp = new SendMailCodePromotionImp();
		sendMailCodePromotionImp.setFrom( "EjemoploMock <noreply@ejemplomock.dummys>"  );
		sendMailCodePromotionImp.setJavaMailSender( javaMailServerMock );
		sendMailCodePromotionImp.setPromotionCodeRepository( promotionCodeRepositoryMock );
		sendMailCodePromotionImp.setMessageSource( messageSourceMock );
		
	}


	///////////////////////////////////////////////////////////////////////////
	///////////////////////////// TESTs OK ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Test
	public void test() {
		
		
		try {
			
			sendMailCodePromotionImp.sendMailCodePromotion( email, locale );
			
			final String sendTo = javaMailServerMock.getSendTo();
			
			boolean res = sendTo != null
							&& sendTo.equals( nameUser.toUpperCase() + " <" + email +">"  );
			
			assertEquals( true, res );
			
		} catch ( Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testOk error ".concat( e.getMessage() ) ); 
		}

	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////// End TESTs OK //////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEmailNull() {
		
		try {
			
			sendMailCodePromotionImp.sendMailCodePromotion( null, locale );
			
			fail ("If I'm here then this is an error");
			
		} catch ( ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testEmailNull: ".concat( message )  );
			
			assertEquals( "validation.error.email.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testEmailNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testLocaleNull() {
		
		try {
			
			sendMailCodePromotionImp.sendMailCodePromotion( email, null );
			
			fail ("If I'm here then this is an error");
			
		} catch ( ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testLocaleNull: ".concat( message )  );
			
			assertEquals( "validation.error.locale.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testLocaleNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionCodeNotBbDd() {
		
		try {
			
			promotionCodeRepositoryMock.setPromotionCode( (PromotionCode) null );
			
			sendMailCodePromotionImp.sendMailCodePromotion( email, locale );
			
			fail ("If I'm here then this is an error");
			
		} catch ( ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodeNotBbDd: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.notbbdd", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodeNull error ".concat( e.getMessage() ) ); 
		}
		
	}

}
