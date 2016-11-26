package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.validation.ValidationError;

import br.com.gpfurlaneto.dto.UserDto;

@Stateless
@Path("/autocadastro")
public class AutoRegisterRest {
	
	@Inject
	private UserRest userRest;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUser(UserDto userDto) {
		if (userDto.getId() != null) {
			ValidationError var = new ValidationError("Erro! você não ter permissões para esta alteração", "", null, null);
			return Response.accepted(var).status(Response.Status.BAD_REQUEST).build();
		}else{
			return userRest.saveUser(userDto);
		}
	}

}
