package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.gpfurlaneto.ChangePasswordService;
import br.com.gpfurlaneto.dto.AlterarSenhaDto;
import br.com.gpfurlaneto.exception.FormException;

@Stateless
@Path("/changePassword")
public class ChangePasswordRest {

	@Inject
	private ChangePasswordService changePasswordService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(AlterarSenhaDto alterarSenhaDto) throws Exception {
		changePasswordService.changePassword(alterarSenhaDto);
		return Response.status(Response.Status.OK).build();
	}
}
