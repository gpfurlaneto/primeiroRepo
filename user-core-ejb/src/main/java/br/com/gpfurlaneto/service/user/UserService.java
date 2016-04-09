package br.com.gpfurlaneto.service.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.constants.entity.User;
import br.com.gpfurlaneto.constants.entity.User_;

@Stateless
public class UserService {

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	public void findAll(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PersonWrapper> criteria = builder.createQuery( PersonWrapper.class );
		Root<User> personRoot = criteria.from( User.class );
		
		criteria.select(
			    builder.construct(
			        PersonWrapper.class,
			        personRoot.get( User_.id),
			        personRoot.get( User_.nome)
			    )
			);
		
		List<PersonWrapper> people = em.createQuery( criteria ).getResultList();
	}
	
}
