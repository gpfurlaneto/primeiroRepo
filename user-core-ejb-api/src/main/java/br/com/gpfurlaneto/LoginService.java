package br.com.gpfurlaneto;

import javax.ejb.Local;

import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.exception.FormException;

@Local
public interface LoginService {

	UserDto loadUser(UserDto userDto);

	UserDto authenticate(UserDto userDto) throws FormException, Exception;

}
