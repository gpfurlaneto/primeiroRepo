package br.com.gpfurlaneto.user.core.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.StringUtils;

import br.com.gpfurlaneto.user.core.annotation.LoginRequired;
import br.com.gpfurlaneto.user.core.util.JWTUtil;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION = "Authorization";
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Class<?> resource = resourceInfo.getResourceClass();
		
		if(loginEhRequerido(resource)){
			validateAuthentication(requestContext);
		}
	}

	private void validateAuthentication(ContainerRequestContext requestContext) {
		String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION);
		if (StringUtils.isBlank(authorizationHeader)) {
			responseCanNotAccess(requestContext);
		}else{
			String[] emailToken = authorizationHeader.split(" ");
			if(!isValidToken(emailToken[0], emailToken[1])){
				responseCanNotAccess(requestContext);
			}
		}
	}

	private boolean isValidToken(String email, String expectedToken) {
		String token = JWTUtil.getToken(email);
		return expectedToken.equals(token);
	}

	private void responseCanNotAccess(ContainerRequestContext requestContext) {
		Response aa = Response.status(Response.Status.UNAUTHORIZED)
				.build(); 
		requestContext.abortWith(aa);
	}

	private boolean loginEhRequerido(Class<?> resource) {
		return resource.isAnnotationPresent(LoginRequired.class);
	}

}
