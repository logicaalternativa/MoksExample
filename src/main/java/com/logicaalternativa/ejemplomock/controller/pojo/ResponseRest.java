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
