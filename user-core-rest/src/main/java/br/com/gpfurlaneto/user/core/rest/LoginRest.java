package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.gpfurlaneto.LoginService;
import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.exception.FormException;
import br.com.gpfurlaneto.user.core.util.JWTUtil;

@Stateless
@Path("/authenticate")
public class LoginRest {

	@Inject
	private LoginService loginService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(UserDto userDto){
		try {
			userDto = loginService.authenticate(userDto);
			userDto.setToken(JWTUtil.getToken(userDto));
			return getResponse(userDto, Response.Status.OK).build();
		}catch(FormException e){
			return getResponse(e.getErrors().values(), Response.Status.OK).build();
		} catch (Exception e) {
			return getResponse(e, Response.Status.BAD_REQUEST).build();
		}
	}

	private ResponseBuilder getResponse(Object entity, Status status) {
		return Response.accepted(entity).status(status);
	}
}
