package br.com.gpfurlaneto.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import br.com.gpfurlaneto.UserService;
import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.entity.User;
import br.com.gpfurlaneto.entity.User_;
import br.com.gpfurlaneto.exception.FormException;
import br.com.gpfurlaneto.util.MessageDigestUtil;

@Stateless
public class UserServiceImpl implements UserService{

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	@Override
	public List<UserDto> listAll(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UserDto> criteria = builder.createQuery( UserDto.class );
		Root<User> userRoot = criteria.from(User.class);
		
		criteria.select(
			    builder.construct(
			        UserDto.class,
			        userRoot.get( User_.id),
			        userRoot.get( User_.nome),
			        userRoot.get( User_.dataNascimento),
			        userRoot.get( User_.email),
			        userRoot.get( User_.login)
			    )
			);
		
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void save(UserDto userDto) throws Exception {
		validateUser(userDto);
		User user = null;
		if (userDto.getId() == null) {
			user = new User();
			user.setSenha(MessageDigestUtil.encrypt(userDto.getSenha()));
		}else{
			user = em.find(User.class, userDto.getId());
		}
		
		user.setDataNascimento(userDto.getDataNascimento());
		user.setEmail(userDto.getEmail());
		user.setLogin(userDto.getLogin());
		user.setNome(userDto.getNome());
		em.merge(user);
	}

	private void validateUser(UserDto userDto) throws FormException {
		List<User> users = loadUser(userDto);
		if (users != null && !users.isEmpty()) {
			User user = users.get(0);
			FormException exception = new FormException();
			if (user.getLogin().equals(userDto.getLogin())) {
				exception.putError("login", "já está sendo utilizado");
			}
			if (user.getEmail().equals(userDto.getEmail())) {
				exception.putError("email", "já está sendo utilizado");
			}
			throw exception;
		}
	}

	private List<User> loadUser(UserDto userDto) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery( User.class );
		Root<User> userRoot = criteria.from(User.class);
		criteria.where(
				builder.or(
							builder.equal(userRoot.get(User_.login), userDto.getLogin()), 
							builder.equal(userRoot.get(User_.email), userDto.getEmail())
							)
						);
		
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(User.class, id));
	}

	@Override
	public String resetPassword(Long id) throws Exception {
		String newPassword = "123456"; 
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
		Root<User> from = update.from(User.class);
		update.set(User_.senha, MessageDigestUtil.encrypt(newPassword));
		update.where(builder.equal(from.get(User_.id), id));
		Query query = em.createQuery(update);
		query.executeUpdate();
		return newPassword;
	}
}
