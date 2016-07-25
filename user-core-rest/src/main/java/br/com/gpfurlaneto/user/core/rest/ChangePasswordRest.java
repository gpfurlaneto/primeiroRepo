package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.ChangePasswordService;
import br.com.gpfurlaneto.dto.AlterarSenhaDto;
import br.com.gpfurlaneto.dto.UserDto;

@Stateless
@Path("/changePassword")
public class ChangePasswordRest {

	@Inject
	private ChangePasswordService changePasswordService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(AlterarSenhaDto alterarSenhaDto){
		
		changePasswordService.changePassword(alterarSenhaDto);
		
		return Response.status(Response.Status.OK).build();
	}
}
