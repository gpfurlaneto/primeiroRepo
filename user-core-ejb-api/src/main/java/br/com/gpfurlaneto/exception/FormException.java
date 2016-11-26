package br.com.gpfurlaneto.exception;

import java.util.HashMap;
import java.util.Map;

public class FormException extends Exception{

	private static final long serialVersionUID = -6885362560483399549L;
	
	private Map<String, String> errors;
	
	public FormException() {
	}
	
	public FormException(Map<String, String> errors){
		this.errors = errors;
	}

	public void putError(String field, String value){
		getErrors().put(field, value);
	}
	
	public Map<String, String> getErrors() {
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		return errors;
	}
	
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}
