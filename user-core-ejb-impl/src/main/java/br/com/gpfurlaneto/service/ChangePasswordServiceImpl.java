package br.com.gpfurlaneto.service;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.gpfurlaneto.ChangePasswordService;
import br.com.gpfurlaneto.dto.AlterarSenhaDto;

@Stateless
public class ChangePasswordServiceImpl implements ChangePasswordService {

	@Override
	public void changePassword(AlterarSenhaDto alterarSenhaDto) {
		System.out.println(1);
	}

}
