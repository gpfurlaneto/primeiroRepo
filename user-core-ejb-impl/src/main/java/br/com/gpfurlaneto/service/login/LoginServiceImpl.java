package br.com.gpfurlaneto.service.login;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.gpfurlaneto.LoginService;
import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.entity.User;
import br.com.gpfurlaneto.entity.User_;
import br.com.gpfurlaneto.exception.LoginException;
import br.com.gpfurlaneto.util.MessageDigestUtil;
import br.com.gpfurlaneto.util.StringUtil;

@Stateless
public class LoginServiceImpl implements LoginService{

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	@Override
	public UserDto authenticate(UserDto userDto) throws LoginException, Exception {
		validateUser(userDto);
		userDto.setSenha(MessageDigestUtil.encrypt(userDto.getSenha()));
		try {
			userDto = loadUser(userDto);
		} catch (NoResultException e){
			throw new Exception("Falha no login! Login ou senha incorreto.");
		}
		return userDto;
	}
	
	@Override
	public UserDto loadUser(UserDto userDto) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UserDto> criteria = builder.createQuery( UserDto.class );
		Root<User> userRoot = criteria.from(User.class);
		criteria.where(builder.equal(userRoot.get(User_.login), userDto.getLogin()));
		criteria.where(builder.equal(userRoot.get(User_.senha), userDto.getSenha()));
		
		criteria.select(
			    builder.construct(
			        UserDto.class,
			        userRoot.get( User_.id),
			        userRoot.get( User_.nome),
			        userRoot.get( User_.login)
			    )
			);
		
		return em.createQuery( criteria ).getSingleResult();
	}

	private void validateUser(UserDto userDto) throws LoginException {
		Map<String, String> errors = new HashMap<String, String>();
		if (StringUtil.isNull(userDto.getLogin())) {
			errors.put("login", "Não pode ser vazio");
		}
		if (StringUtil.isNull(userDto.getSenha())) {
			errors.put("senha", "Não pode ser vazio");
		}
		if (!errors.isEmpty()) {
			throw new LoginException(errors);
		}
	}
}
