package br.com.gpfurlaneto.user.core.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.validation.ValidationError;

import br.com.gpfurlaneto.exception.FormException;

@Provider
public class FormExceptionMapper implements ExceptionMapper<FormException> {

	@Override
	public Response toResponse(FormException exception) {
		List<ValidationError> errors = configErrorsPath(exception.getErrors());
		return Response.status(Response.Status.ACCEPTED).entity(errors).build();
	}

	private List<ValidationError> configErrorsPath(Map<String, String> errors) {
		List<ValidationError> newErrors = new ArrayList<ValidationError>();
		for (Entry<String, String> error : errors.entrySet()) {
			newErrors.add(new ValidationError(error.getValue(), error.getValue(), error.getKey(), null));
		}
		return newErrors;
	}
}
