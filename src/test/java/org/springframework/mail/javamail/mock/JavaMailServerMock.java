/*
 *      JavaMailServerMock.java
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
package org.springframework.mail.javamail.mock;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class JavaMailServerMock implements JavaMailSender {
	
	private String sendTo;

	protected  static Logger logger = LoggerFactory.getLogger( JavaMailServerMock.class );

	@Override
	public void send(SimpleMailMessage arg0) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(SimpleMailMessage[] arg0) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MimeMessage createMimeMessage() {
		
		final Session session = Session.getInstance( new Properties() );
		
		final MimeMessage mimeMessage = new MimeMessage(session );
		
		return mimeMessage;
	}

	@Override
	public MimeMessage createMimeMessage(InputStream arg0) throws MailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send( MimeMessage mimeMessage ) throws MailException {
		
		String[] to = null;
		
		try {
			
			to = mimeMessage.getHeader("To");
			
		} catch (MessagingException e) {
			
			logger.error( "[send] ".concat( e.getMessage() ), e );  
			
			to = null;			
		}
		
		setSendTo( to != null && to.length > 0 ? to[0] : null );
		
	}

	@Override
	public void send(MimeMessage[] arg0) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(MimeMessagePreparator arg0) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(MimeMessagePreparator[] arg0) throws MailException {
		// TODO Auto-generated method stub
		
	}
	
	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

}
