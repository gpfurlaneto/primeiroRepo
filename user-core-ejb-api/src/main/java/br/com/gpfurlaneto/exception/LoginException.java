package br.com.gpfurlaneto.exception;

import java.util.Map;

public class LoginException extends Exception{

	private Map<String, String> errors;

	public LoginException(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
}
