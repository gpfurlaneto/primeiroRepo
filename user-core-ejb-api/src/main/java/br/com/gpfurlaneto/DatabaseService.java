package br.com.gpfurlaneto;

import javax.ejb.Local;

@Local
public interface DatabaseService {

	boolean isDatabaseUpdated();

	void updateDatabase();

	void updateDatabaseVersion();

}
