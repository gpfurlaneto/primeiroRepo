package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.dto.UserDto;
import br.com.gpfurlaneto.service.database.DatabaseService;

@Path("/user")
public class UserRest {

	@EJB
	private DatabaseService databaseService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg() {
		UserDto dto = new UserDto();
		dto.setId(1L);
		dto.setName("Test Name");
		return Response.status(200).entity(dto).build();

	}
}
