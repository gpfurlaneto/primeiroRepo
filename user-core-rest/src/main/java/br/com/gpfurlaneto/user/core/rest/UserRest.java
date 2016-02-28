package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.service.UserService;

import javax.ws.rs.Produces;

@Path("/user")
public class UserRest {

	@EJB
	private UserService userService;
	
	
	@GET
	@Produces("text/plain")
	public Response getMsg() {

		String output = userService.getOutPut();

		return Response.status(200).entity(output).build();

	}
}
