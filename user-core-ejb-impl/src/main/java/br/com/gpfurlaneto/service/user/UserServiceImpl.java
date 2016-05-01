package br.com.gpfurlaneto.service.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.Validator;

import br.com.gpfurlaneto.UserService;
import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.entity.User;
import br.com.gpfurlaneto.entity.User_;

@Stateless
public class UserServiceImpl implements UserService{

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	public List<UserDto> listAll(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UserDto> criteria = builder.createQuery( UserDto.class );
		Root<User> userRoot = criteria.from( User.class );
		
		criteria.select(
			    builder.construct(
			        UserDto.class,
			        userRoot.get( User_.id),
			        userRoot.get( User_.nome),
			        userRoot.get( User_.dataNascimento),
			        userRoot.get( User_.email),
			        userRoot.get( User_.login),
			        userRoot.get( User_.senha)
			    )
			);
		
		return em.createQuery( criteria ).getResultList();
	}

	@Override
	public void save(UserDto userDto) {
		User user = null;
		if (userDto.getId() == null) {
			user = new User();
		}else{
			user = em.find(User.class, userDto.getId());
		}
		
		user.setDataNascimento(userDto.getDataNascimento());
		user.setEmail(userDto.getEmail());
		user.setLogin(userDto.getLogin());
		user.setNome(userDto.getNome());
		user.setSenha(userDto.getSenha());
		em.merge(user);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(User.class, id));
	}
	
}
