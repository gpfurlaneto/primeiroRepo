package br.com.gpfurlaneto.user.core.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.gpfurlaneto.user.core.annotation.LoginRequired;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Class<?> resource = resourceInfo.getResourceClass();
		if(resource.isAnnotationPresent(LoginRequired.class)){
			aa = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build(); 
			requestContext.abortWith(aa);
		}
	}

}
