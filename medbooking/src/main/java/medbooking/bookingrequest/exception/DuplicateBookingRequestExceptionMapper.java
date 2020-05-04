package medbooking.bookingrequest.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import medbooking.ui.model.response.ErrorMessages;
import medbooking.ui.model.response.ErrorResponse;

@Provider
public class DuplicateBookingRequestExceptionMapper implements ExceptionMapper<DuplicateBookingRequestException>{
	
	@Override
	@Produces(MediaType.APPLICATION_XML)
	public Response toResponse(DuplicateBookingRequestException exception) {
		
		ErrorResponse error= new ErrorResponse(exception.getMessage(), 
				ErrorMessages.ALREADY_EXIST.name(), "http://documentation.errordetials.com");
		
		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
	}



}
