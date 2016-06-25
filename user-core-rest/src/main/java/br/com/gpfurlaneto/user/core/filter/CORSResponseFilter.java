package br.com.gpfurlaneto.user.core.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;


@Provider
@PreMatching
public class CORSResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "DELETE, *");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization");
        String reqHeader = requestContext.getHeaderString("Access-Control-Request-Headers");
        if (reqHeader != null && reqHeader != "") {
            responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", reqHeader);
        }
	        
	}

}
