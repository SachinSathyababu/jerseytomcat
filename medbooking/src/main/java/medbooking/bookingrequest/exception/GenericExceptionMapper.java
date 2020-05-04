package medbooking.bookingrequest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import medbooking.ui.model.response.ErrorMessages;
import medbooking.ui.model.response.ErrorResponse;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ErrorResponse error= new ErrorResponse(exception.getMessage(), 
				ErrorMessages.INTERNAL_SERVER_ERROR.name(), "http://documentation.errordetials.com");
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

	

}
