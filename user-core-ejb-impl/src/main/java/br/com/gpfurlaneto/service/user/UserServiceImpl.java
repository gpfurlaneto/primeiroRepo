package br.com.gpfurlaneto.service.user;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.gpfurlaneto.UserService;
import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.entity.User;
import br.com.gpfurlaneto.entity.User_;

@Stateless
public class UserServiceImpl implements UserService{

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	public List<UserDto> findAll(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UserDto> criteria = builder.createQuery( UserDto.class );
		Root<User> personRoot = criteria.from( User.class );
		
		criteria.select(
			    builder.construct(
			        UserDto.class,
			        personRoot.get( User_.id),
			        personRoot.get( User_.nome)
			    )
			);
		
		return em.createQuery( criteria ).getResultList();
	}
	
}
