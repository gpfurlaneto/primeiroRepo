package br.com.gpfurlaneto.user.core.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.validation.ValidationError;
import org.glassfish.jersey.server.validation.internal.ValidationHelper;

@Provider
public class EJBTransactionRolledbackExceptionMapper implements ExceptionMapper<EJBTransactionRolledbackException> {

	@Override
	public Response toResponse(EJBTransactionRolledbackException exception) {
		Throwable throwable = exception.getCause();
		if (throwable != null && throwable.getCause() != null) {
			Throwable throwablePersistence = throwable.getCause();
			if (throwablePersistence != null && throwablePersistence.getCause() != null
					&& throwablePersistence.getCause() instanceof ConstraintViolationException) {

				ConstraintViolationException cve = (ConstraintViolationException) throwablePersistence.getCause();
				List<ValidationError> errors = configErrorsPath(ValidationHelper.constraintViolationToValidationErrors(cve));
				configErrorsPath(errors);
				return Response.status(Response.Status.ACCEPTED)
						.entity(errors).build();
			}
		}
		return Response.status(200).build();
	}

	private List<ValidationError> configErrorsPath(List<ValidationError> errors) {
		List<ValidationError> newErrors = new ArrayList<ValidationError>();
		for (ValidationError error : errors) {
			String path = error.getPath().split("\\.")[error.getPath().split("\\.").length -1];
			newErrors.add(new ValidationError(error.getMessage(), error.getMessageTemplate(), path, error.getInvalidValue()));
		}
		return newErrors;
	}
	
}
