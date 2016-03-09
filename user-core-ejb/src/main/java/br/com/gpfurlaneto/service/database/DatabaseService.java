package br.com.gpfurlaneto.service.database;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.constants.entity.Setting;
import br.com.gpfurlaneto.constants.entity.Setting_;
import br.com.gpfurlaneto.database.builder.DatabaseBuilder;
import br.com.gpfurlaneto.database.version.SystemVersion;

@Stateless
public class DatabaseService {

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Resource(mappedName = UserCoreConstants.CONFIG_DATABASE_DATASOURCE)
	protected DataSource datasource;

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void updateDatabase() {
		try{
			DatabaseBuilder builder = new DatabaseBuilder();
			builder.buildDatabase(datasource.getConnection());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void updateDatabaseVersion(){
		Setting setting = this.getLastVersionEntityFromDatabase();
		setting.setValue(SystemVersion.getLastVersion().getCode());
		em.merge(setting);
	}
	

	public boolean isDatabaseUpdated() {
		boolean exists = ((BigInteger) em.createNativeQuery(
				"SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'TB_SETTING'")
		.getSingleResult()).intValue() > 0;
		
		if (exists) {
			SystemVersion lastVersion =  SystemVersion.getLastVersion();
			return getLastVersionFromDatabase().equals(lastVersion.getCode());
		}
		return exists;
	}
	
	private Setting getLastVersionEntityFromDatabase() {
		CriteriaQuery<Setting> criteria = getCriteriaLastVersion();
		return em.createQuery(criteria).getSingleResult();
	}

	private CriteriaQuery<Setting> getCriteriaLastVersion() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Setting> criteria = builder.createQuery(Setting.class);
		Root<Setting> selectRoot = criteria.from(Setting.class);
		criteria.select(selectRoot);
		criteria.where(builder.equal(selectRoot.get(Setting_.constant), UserCoreConstants.CONFIG_DATABASE_SYSTEM_VERSION) );
		return criteria;
	}
	
	public String getLastVersionFromDatabase() {
		return getLastVersionEntityFromDatabase().getValue();
	}
}
