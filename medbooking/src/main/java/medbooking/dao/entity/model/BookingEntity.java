package medbooking.dao.entity.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Bookings")
public class BookingEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1891205389679932031L;

	@Id
	@GeneratedValue
	private long id;

	private String bookingId;
	private String patientName;
	private String patientEmail;
	private String doctorName;
	private LocalDate bookingDateTime;
	private String bookingSummay;
	private String hospitalName;
	
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
	
	
	
}
