package br.com.gpfurlaneto.service;

import javax.ejb.Stateless;

@Stateless
public class UserService {

	public String getOutPut() {
		return "Jersey say EJB";
	}

	
}
