package medbooking.ui.model.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import medbooking.shared.dto.Bookingdto.bookingStatus;

@XmlRootElement
public class BookingResponse {

	private String bookingId;
	private String patientName;
	private String patientEmail;
	private String doctorName;
	
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	private LocalDate bookingDateTime;
	private String bookingSummay;
	private String hospitalName;
	private String href;
	
	private bookingStatus status;
	
	public BookingResponse() {
	}
	
	public BookingResponse(String bookingId, String patientName, String patientEmail, String doctorName,
			LocalDate bookingDateTime, String bookingSummay, String hospitalName, String href, bookingStatus status) {
		super();
		this.bookingId = bookingId;
		this.patientName = patientName;
		this.patientEmail = patientEmail;
		this.doctorName = doctorName;
		this.bookingDateTime = bookingDateTime;
		this.bookingSummay = bookingSummay;
		this.hospitalName = hospitalName;
		this.href = href;
		this.status = status;
	}

	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public LocalDate getBookingDateTime() {
		return bookingDateTime;
	}
	public void setBookingDateTime(String bookingDateTime) {
		this.bookingDateTime = LocalDate.parse(bookingDateTime, DateTimeFormatter.ISO_LOCAL_DATE);
	}
	public String getBookingSummay() {
		return bookingSummay;
	}
	public void setBookingSummay(String bookingSummay) {
		this.bookingSummay = bookingSummay;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public bookingStatus getStatus() {
		return status;
	}
	public void setStatus(bookingStatus status) {
		this.status = status;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	
}
