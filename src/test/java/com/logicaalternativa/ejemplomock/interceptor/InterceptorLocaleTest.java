package com.logicaalternativa.ejemplomock.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.mok.HttpServletRequestMock;
import javax.servlet.http.mok.HttpServletResponseMock;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterceptorLocaleTest {
	
	protected  static Logger logger = LoggerFactory.getLogger( InterceptorLocaleTest.class );
	
	private InterceptorLocale interceptorLocale;
	
	private Map<String, String> headersMock;
	
	private HttpServletRequestMock request;
	
	private HttpServletResponseMock response;
	
	private Object handler;

	@Before
	public void setUp() throws Exception {
		
		interceptorLocale = new InterceptorLocale();
		
		headersMock = new HashMap<String,String>();
		headersMock.put( "Locale-user", "es" );
		
		request = new HttpServletRequestMock();
		request.setHeadersMock( headersMock ) ;
		
		response = new HttpServletResponseMock();
		
		handler = new Object();
		
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////// TESTs OK ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	@Test
	public void test() {
		
		try {
			
			interceptorLocale.preHandle( request, response, handler );
			
			final Object attributeLocaleUser = request.getAttribute( "localeUser" );
			
			final Locale localeEs =  new Locale( "es" );
			
			boolean result = attributeLocaleUser != null
					      && attributeLocaleUser instanceof Locale
					      && localeEs.equals( ( Locale ) attributeLocaleUser  )
					      ;
							
			
			assertEquals( true, result );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testOk error ".concat( e.getMessage() ) ); 
			
		}
	}
	
	@Test
	public void testOkNoHeader() {
		
		try {
			
			headersMock.remove( "Locale-user");
			
			interceptorLocale.preHandle( request, response, handler );
			
			final Object attributeLocaleUser = request.getAttribute( "localeUser" );
			
			final Locale localeEs =  new Locale( "en" );
			
			boolean result = attributeLocaleUser != null
					      && attributeLocaleUser instanceof Locale
					      && localeEs.equals( ( Locale ) attributeLocaleUser  )
					      ;
							
			
			assertEquals( true, result );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testOk error ".concat( e.getMessage() ) ); 
			
		}
	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////// End TESTs OK //////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testRequestNull() {
		
		try {
			
			interceptorLocale.preHandle( null, response, handler );
			
			fail ("If I'm here then this is an error");
			
		} catch (Exception e) {
			
			final String message = e.getMessage();
			
			logger.info( "testRequestNull: ".concat( message )  );
			
			assertEquals( "Request is null", message );
			
		}
		
	}
	

}
