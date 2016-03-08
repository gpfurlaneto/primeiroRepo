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

	public String buildDatabase(Connection connection) throws Exception {

		SystemVersion newSystemVersion = SystemVersion.V0_0_1;
		SystemVersion lastVersion = newSystemVersion;
		
		while (newSystemVersion != null) {
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			
			if (newSystemVersion.hasChangeLogPre()) {
				Liquibase liquibase = new liquibase.Liquibase(newSystemVersion.getChangeLogPre(), new ClassLoaderResourceAccessor(), database);
				liquibase.update(new Contexts(), new LabelExpression());
			}
			
			if (newSystemVersion.hasChangeLogPos()) {
				Liquibase liquibase = new liquibase.Liquibase(newSystemVersion.getChangeLogPos(), new ClassLoaderResourceAccessor(), database);
				liquibase.update(new Contexts(), new LabelExpression());
				
			}
			
			newSystemVersion = newSystemVersion.getPostSystemVersion();
			if (newSystemVersion != null) {
				lastVersion = newSystemVersion;
			}
		}
		return lastVersion.getCode();
		
	}
}
