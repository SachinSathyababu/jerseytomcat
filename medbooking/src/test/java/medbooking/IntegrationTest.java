package medbooking;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import medbooking.ui.model.response.BookingResponse;

public class IntegrationTest {
	
	@Test
	public void  testIntegartionforGET() throws IOException {
		URL url = new URL("http://localhost:8080/medbooking/api/booking/irz9wYHR" );
		String response= new String();
		HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
		urlConnect.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtZWRib29raW5nLmFwaSIsImlhdCI6MTU4OTM2MTg0OSwic3ViIjoiYXBwdXNlciIsInJvbGVzIjoidXNlciIsImlzcyI6Im1lZGJvb2tpbmciLCJleHAiOjE1ODkzNjU0NDl9.jnC_mrWc1jpBSnptb6XBgWQ18TOWiHijPnyfzQ8hThs");
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
		urlConnect.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtZWRib29raW5nLmFwaSIsImlhdCI6MTU4OTM2MTg0OSwic3ViIjoiYXBwdXNlciIsInJvbGVzIjoidXNlciIsImlzcyI6Im1lZGJvb2tpbmciLCJleHAiOjE1ODkzNjU0NDl9.jnC_mrWc1jpBSnptb6XBgWQ18TOWiHijPnyfzQ8hThs");
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
