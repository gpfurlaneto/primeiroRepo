package br.com.gpfurlaneto;

import javax.ejb.Local;
import javax.security.auth.login.LoginException;

import br.com.gpfurlaneto.dto.UserDto;

@Local
public interface LoginService {

	UserDto loadUser(UserDto userDto);

	UserDto authenticate(UserDto userDto) throws LoginException, Exception;

}
