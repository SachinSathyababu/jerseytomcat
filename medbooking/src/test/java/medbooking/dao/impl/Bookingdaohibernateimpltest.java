package medbooking.dao.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import medbooking.dao.BookingDao;
import medbooking.shared.dto.Bookingdto;
import medbooking.ui.model.response.BookingResponse;

@RunWith(MockitoJUnitRunner.class)
public class Bookingdaohibernateimpltest{
	
	@Mock
	BookingDao impl;
	
	@Test
	public void simpleTestsucess() {
		
		
		
	}
	
	
	@Test
	public void simpleTestfailure() {
		
		//fail("not yet implemented");
		
	}
	
	@Test
	public void sumTest() {
		
		Bookingdaohibernateimpl impl= new Bookingdaohibernateimpl();
		int actualResult= impl.sum(1, 3);
		
		assertEquals(4, actualResult);
		
	}
	
	@Test
	public void getBookingByIdTest() {
		
		Bookingdto book= new Bookingdto();
		book.setDoctorName("Anitha");
		Mockito.when(impl.getBookingById("irz9wYHR")).thenReturn(book);
		
		String actual= impl.getBookingById("irz9wYHR").getDoctorName();
		assertEquals("Anitha", actual);
		
		
	}
	
	@Test
	public void  testIntegartionforGET() throws IOException {
		URL url = new URL("http://localhost:8080/medbooking/api/booking/irz9wYHR" );
		String response= new String();
		HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
		urlConnect.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtZWRib29raW5nLmFwaSIsImlhdCI6MTU4OTI3MjM5Miwic3ViIjoiYXBwdXNlciIsInJvbGVzIjoidXNlciIsImlzcyI6Im1lZGJvb2tpbmciLCJleHAiOjE1ODkyNzU5OTJ9.JUWvJnuzzEHt2-Xqj2SHwHKOYNz_pdtBthl3uoBUrgA");
		urlConnect.setRequestProperty("Accept", "application/json");
		
		InputStream is = urlConnect.getInputStream();
		
		int i = 0;
		while((i =  is.read()) != -1 ) {
			response=response+(char)i;
			System.out.print((char)i);
		}
		
		try {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
	        BookingResponse jsonMap = mapper.readValue(response, BookingResponse.class);

	        System.out.println(jsonMap.getBookingId());
	       assertEquals("irz9wYHR", jsonMap.getBookingId());
	    } finally {
	        is.close();
	    }
		
	}
	
	@Test
	public void  testIntegartionforPOST() throws IOException {
		URL url = new URL("http://localhost:8080/medbooking/api/booking" );
		String response= new String();
		HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
		urlConnect.setRequestMethod("POST");
		urlConnect.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtZWRib29raW5nLmFwaSIsImlhdCI6MTU4OTI3MjM5Miwic3ViIjoiYXBwdXNlciIsInJvbGVzIjoidXNlciIsImlzcyI6Im1lZGJvb2tpbmciLCJleHAiOjE1ODkyNzU5OTJ9.JUWvJnuzzEHt2-Xqj2SHwHKOYNz_pdtBthl3uoBUrgA");
		urlConnect.setRequestProperty("Content-Type", "application/json");
		urlConnect.setRequestProperty("Accept", "application/json");
		urlConnect.setDoOutput(true);
		
		String jsonInputString = "{\r\n" + 
				"	\r\n" + 
				"	\"patientName\":\"Sachin\",\r\n" + 
				"	\"patientEmail\":\"sach.203@gmail.com\",\r\n" + 
				"	\"doctorName\":\"Arya\",\r\n" + 
				"	\"bookingDateTime\":\"2020-05-20\",\r\n" + 
				"	\"bookingSummay\":\"Stomach Infection\",\r\n" + 
				"	\"hospitalName\":\"KIMS\"\r\n" + 
				"	\r\n" + 
				"}\r\n";
		
		try(OutputStream os = urlConnect.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);           
		}
		
		InputStream is = urlConnect.getInputStream();
		
		int i = 0;
		while((i =  is.read()) != -1 ) {
			response=response+(char)i;
			System.out.print((char)i);
		}
		
		try {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
	        BookingResponse jsonMap = mapper.readValue(response, BookingResponse.class);

	        System.out.println(jsonMap.getBookingId());
	       assertEquals("Arya", jsonMap.getDoctorName());
	    } finally {
	        is.close();
	    }
		
	}

}
