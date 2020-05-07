package medbooking.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class Bookingdto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String bookingId;
	private String patientName;
	private String patientEmail;
	private String doctorName;
	private LocalDate bookingDateTime;
	private String bookingSummay;
	private String hospitalName;
	
	public enum bookingStatus {CONFIRMED,CANCELLED};
	
	private bookingStatus status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public void setBookingDateTime(LocalDate bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
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
	
	
}
