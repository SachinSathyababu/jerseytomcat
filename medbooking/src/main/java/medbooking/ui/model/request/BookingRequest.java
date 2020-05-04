package medbooking.ui.model.request;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;

import medbooking.bookingrequest.utils.LocalDateAdapter;



@XmlRootElement
public class BookingRequest {

	private String patientName;
	private String patientEmail;
	private String doctorName;
	
	@JsonFormat(pattern="ddMMyyyy")
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate bookingDateTime;
	private String bookingSummay;
	private String hospitalName;
	
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
