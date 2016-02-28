package br.com.gpfurlaneto.database.builder;

import javax.activation.DataSource;
import javax.annotation.Resource;

import liquibase.Liquibase;

public class DatabaseBuilder {

	@Resource
	private DataSource ds;
	
	
	
	
	public void Builder() {
		Liquibase liquibase = new Liquibase("", ds.);
		liquibase.update("");
	}

}
