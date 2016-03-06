package br.com.gpfurlaneto.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.constants.entity.Setting;
import br.com.gpfurlaneto.constants.entity.Setting_;
import br.com.gpfurlaneto.database.builder.DatabaseBuilder;

@Stateless
public class UserService {

	@Resource(mappedName=UserCoreConstants.CONFIG_DATABASE_DATASOURCE) 
	protected DataSource datasource;
	
	@PersistenceContext(unitName=UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String getOutPut() {
		
//		CREATE TABLE TB_SETTING (ID_SETTING  INT PRIMARY KEY, NM_SETTING VARCHAR(150), VL_SETTING VARCHAR(150), DS_CONSTANT VARCHAR(150));
//		INSERT INTO TB_SETTING (ID_SETTING, NM_SETTING, VL_SETTING, DS_CONSTANT) VALUES (1, 'System Version', NULL, 'SYSTEM_VERSION');

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Setting> criteriaQuery = criteriaBuilder.createQuery(Setting.class);
		Root<Setting> setting = criteriaQuery.from(Setting.class);
		criteriaQuery.select(setting).where(criteriaBuilder.equal(setting.get(Setting_.constant), UserCoreConstants.CONFIG_DATABASE_SYSTEM_VERSION));
		TypedQuery<Setting> typedQuery = em.createQuery(criteriaQuery);
		Setting settingDatabaseVersion = typedQuery.getSingleResult();
		
		DatabaseBuilder builder = new DatabaseBuilder();
		try {
			
			builder.buildDatabase(datasource.getConnection(), settingDatabaseVersion != null ? settingDatabaseVersion.getValue() : null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return "Jersey say EJB";
	}

	
}
