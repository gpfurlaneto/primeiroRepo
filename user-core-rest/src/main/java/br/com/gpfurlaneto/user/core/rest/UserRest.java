package br.com.gpfurlaneto.user.core.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.UserService;
import br.com.gpfurlaneto.dto.UserDto;

@Stateless
@Path("/user")
public class UserRest {

	@Inject
	private UserService userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg() {
		List<UserDto> users = userService.findAll();
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(users).build();
	}
	
}
