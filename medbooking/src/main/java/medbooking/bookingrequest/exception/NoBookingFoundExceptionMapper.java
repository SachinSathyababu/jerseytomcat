package medbooking.bookingrequest.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import medbooking.ui.model.response.ErrorMessages;
import medbooking.ui.model.response.ErrorResponse;

@Provider
public class NoBookingFoundExceptionMapper implements ExceptionMapper<NoBookingFoundException>{
	
	@Override
	@Produces(MediaType.APPLICATION_XML)
	public Response toResponse(NoBookingFoundException exception) {
		
		ErrorResponse error= new ErrorResponse(exception.getMessage(), 
				ErrorMessages.NO_BOOKING_FOUND.name(), "http://documentation.errordetials.com");
		
		return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
	}



}
