/*
 *      ResponseRest.java
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
package com.logicaalternativa.ejemplomock.controller.pojo;

import java.io.Serializable;

public class ResponseRest <T> implements Serializable {
	
	
	
	public ResponseRest() {
		super();
	}

	public ResponseRest(String codeResult, T response) {
		super();
		this.codeResult = codeResult;
		this.response = response;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6802711229896453947L;

	public static String OK = "OK";
	
	public static String VALIDATION_ERROR = "validation_error";
	
	public static String GENERIC_ERROR = "generic_error";
	
	private String codeResult;
	
	private T response;

	public String getCodeResult() {
		return codeResult;
	}

	public void setCodeResult(String codeResult) {
		this.codeResult = codeResult;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

}
