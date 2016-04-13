package br.com.gpfurlaneto.user.core.rest.initializer;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.gpfurlaneto.DatabaseService;

@WebListener
public class UserCoreInitializer implements ServletContextListener {

	@EJB
	private DatabaseService databaseService;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		if (!databaseService.isDatabaseUpdated()) {
			databaseService.updateDatabase();
			databaseService.updateDatabaseVersion();
		}
	}

}
