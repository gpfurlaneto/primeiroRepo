package br.com.gpfurlaneto.user.core.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.service.database.DatabaseService;
import br.com.gpfurlaneto.service.user.UserService;

@Path("/user")
public class UserRest {

	@EJB
	private UserService userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg() {
		
		userService.findAll();
		UserDto dto = new UserDto();
		dto.setId(1L);
		dto.setNome("Guilherme De Pieri Furlaneto");
		dto.setLogin("gpfurlaneto");
		dto.setDataNascimento(new Date());
		dto.setEmail("gpfurlaneto@gmail.com");
		dto.setSenha("teste");
		
		List<UserDto> users = new ArrayList<UserDto>();
		users.add(dto);

		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(users).build();

	}
}
