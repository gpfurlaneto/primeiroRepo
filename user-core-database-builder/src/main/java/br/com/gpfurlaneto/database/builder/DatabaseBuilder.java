package br.com.gpfurlaneto.database.builder;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import br.com.gpfurlaneto.database.version.SystemVersion;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class DatabaseBuilder {

	@Resource
	private DataSource ds;

	public void buildDatabase(Connection connection, String currentVersionCode) throws Exception {

		SystemVersion currentSystemVersion = SystemVersion.valueOfCode(currentVersionCode);
		SystemVersion newSystemVersion = null;
		
		if (currentSystemVersion == null) {
			newSystemVersion = SystemVersion.V0_0_1;
		}else{
			newSystemVersion = currentSystemVersion.getPostSystemVersion();
		}
		
		while (newSystemVersion != null) {
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			Liquibase liquibase = new liquibase.Liquibase(newSystemVersion.getChangeLog(), new ClassLoaderResourceAccessor(), database);
			liquibase.update(new Contexts(), new LabelExpression());
			newSystemVersion = newSystemVersion.getPostSystemVersion();
		}
	}
}
