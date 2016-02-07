package br.com.gpfurlaneto.user.core.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;

@Path("/user")
public class UserService {

	@GET
	@Produces("text/plain")
	public Response getMsg() {

		String output = "Jersey say";

		return Response.status(200).entity(output).build();

	}
}
