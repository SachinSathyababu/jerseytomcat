package medbooking.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeanUtils;

import medbooking.service.BookingService;
import medbooking.service.impl.BookingServiceImpl;
import medbooking.shared.dto.Bookingdto;
import medbooking.ui.model.request.BookingRequest;
import medbooking.ui.model.response.BookingResponse;

@Path("/booking")
public class BookingResource {

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public BookingResponse createBooking(BookingRequest bookingRequest) {
		BookingResponse response= new BookingResponse();
		
		//prepare bookingdto
		Bookingdto bookdto = new Bookingdto();
		BeanUtils.copyProperties(bookingRequest, bookdto);
		
		//create booking
		BookingService bookingService= new BookingServiceImpl();
		Bookingdto createdbookdto = bookingService.createBooking(bookdto);
		
		//prepare response
		BeanUtils.copyProperties(createdbookdto, response);
		
		return response;
	}
	
	@GET
	@Path("/{bookingId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public BookingResponse getBookingById(@PathParam("bookingId") String bookingId) {
		BookingResponse response= new BookingResponse();
		
		//Rertrieve booking details
			BookingService bookingService= new BookingServiceImpl();
			Bookingdto bookdto = bookingService.getBookingByBookingId(bookingId);
		
		//prepare response
			BeanUtils.copyProperties(bookdto, response);
		return response;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<BookingResponse> getBookingsPerPage(@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("1") @QueryParam("limit") int limit) {
		List<BookingResponse> response= new ArrayList<BookingResponse>();
		
		//Retrieve all booking details per page
			BookingService bookingService= new BookingServiceImpl();
			List<Bookingdto> bookdtoList = bookingService.getBookings(start, limit);
		
		//prepare response
			for(Bookingdto bookingdto: bookdtoList) {
				BookingResponse eachresponse= new BookingResponse();
				BeanUtils.copyProperties(bookingdto, eachresponse);
				eachresponse.setHref("/booking/"+eachresponse.getBookingId());
				response.add(eachresponse);
			}
			
		return response;
	}
	
}
