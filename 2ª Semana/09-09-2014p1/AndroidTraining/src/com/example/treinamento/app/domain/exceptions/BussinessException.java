package com.example.treinamento.app.domain.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

public class BussinessException extends Exception{

	/**
	 * Serial ID 
	 */
	private static final long serialVersionUID = 8326509518579054770L;
	
	private final Map<Integer, Integer> exceptionMap = new LinkedHashMap<>();

	public Map<Integer, Integer> getExceptionMap() {
		return exceptionMap;
	}
	
}
