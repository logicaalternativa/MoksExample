package com.logicaalternativa.ejemplomock.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InterceptorLocale  extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if ( request == null ) {
			
			throw new Exception( "Request is null" );
			
		}
		
		final String localeUser = request.getHeader("Locale-user");
		
		final Locale locale = localeUser != null ? new Locale( localeUser ) : new Locale("en");
		
		request.setAttribute( "localeUser", locale );
		
		return super.preHandle(request, response, handler);
	}
	
	

}
