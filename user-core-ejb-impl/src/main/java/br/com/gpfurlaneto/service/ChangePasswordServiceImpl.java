package br.com.gpfurlaneto.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import br.com.gpfurlaneto.ChangePasswordService;
import br.com.gpfurlaneto.constants.UserCoreConstants;
import br.com.gpfurlaneto.dto.AlterarSenhaDto;
import br.com.gpfurlaneto.entity.User;
import br.com.gpfurlaneto.entity.User_;
import br.com.gpfurlaneto.exception.FormException;
import br.com.gpfurlaneto.util.MessageDigestUtil;

@Stateless
public class ChangePasswordServiceImpl implements ChangePasswordService {

	@PersistenceContext(unitName = UserCoreConstants.CONFIG_DATABASE_PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Override
	public void changePassword(AlterarSenhaDto alterarSenhaDto) throws Exception {
		validate(alterarSenhaDto);
		String newPassword = MessageDigestUtil.encrypt(alterarSenhaDto.getNovaSenha());
		updateNewPassword(alterarSenhaDto.getLogin(), newPassword);
	}

	private void validate(AlterarSenhaDto alterarSenhaDto) throws Exception {
		Map<String, String> errors = new HashMap<>();
		validateAlterarSenha(alterarSenhaDto, errors);
		validateUser(alterarSenhaDto.getLogin(), alterarSenhaDto.getSenhaAtual(), errors);
		validateSamePasswords(alterarSenhaDto.getNovaSenha(), alterarSenhaDto.getConfirmarSenha(), errors);
		if (!errors.isEmpty()) {
			throw new FormException(errors);
		}
	}

	private void validateAlterarSenha(AlterarSenhaDto alterarSenhaDto, Map<String, String> errors)
			throws FormException {
		if (StringUtils.isBlank(alterarSenhaDto.getLogin())) {
			errors.put("login", "Não pode ser vazio");
		}
		if (StringUtils.isBlank(alterarSenhaDto.getSenhaAtual())) {
			errors.put("senhaAtual", "Não pode ser vazio");
		}
		if (StringUtils.isBlank(alterarSenhaDto.getNovaSenha())) {
			errors.put("novaSenha", "Não pode ser vazio");
		}
		if (StringUtils.isBlank(alterarSenhaDto.getConfirmarSenha())) {
			errors.put("confirmarSenha", "Não pode ser vazio");
		}
	}

	private void validateUser(String login, String senha, Map<String, String> errors) throws Exception {
		senha = MessageDigestUtil.encrypt(senha);

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.where(builder.and(builder.equal(userRoot.get(User_.login), login),
				builder.equal(userRoot.get(User_.senha), senha)));
		criteria.multiselect(userRoot.get(User_.id));
		try {
			em.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			errors.put("senhaAtual", "está incorreto");
		}
	}

	private void validateSamePasswords(String novaSenha, String confirmarSenha, Map<String, String> errors)
			throws FormException {
		if (StringUtils.isBlank(novaSenha) || !novaSenha.equals(confirmarSenha)) {
			errors.put("novaSenha", "está diferente de confirmar senha");
		}
	}

	private void updateNewPassword(String login, String newPassword) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
		Root<User> from = update.from(User.class);
		update.set(User_.senha, newPassword);
		update.where(builder.equal(from.get(User_.login), login));
		Query query = em.createQuery(update);
		query.executeUpdate();
	}

}
