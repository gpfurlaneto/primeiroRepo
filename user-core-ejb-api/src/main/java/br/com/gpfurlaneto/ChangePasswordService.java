package br.com.gpfurlaneto;

import javax.ejb.Local;

import br.com.gpfurlaneto.dto.AlterarSenhaDto;

@Local
public interface ChangePasswordService {

	void changePassword(AlterarSenhaDto alterarSenhaDto);
}
