package br.com.gpfurlaneto.user.core.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.service.database.DatabaseService;

@Path("/user")
public class UserRest {

	@EJB
	private DatabaseService databaseService;
	
	@GET
	@Produces("text/plain")
	public Response getMsg() {
		return Response.status(200).entity(databaseService.getLastVersionFromDatabase()).build();

	}
}
