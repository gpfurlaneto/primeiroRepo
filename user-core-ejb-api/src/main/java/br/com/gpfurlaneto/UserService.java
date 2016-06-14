package br.com.gpfurlaneto;

import java.util.List;

import javax.ejb.Local;

import br.com.gpfurlaneto.dto.UserDto;

@Local
public interface UserService {

	List<UserDto> listAll();

	void save(UserDto userDto) throws Exception;

	void delete(Long id);

}
