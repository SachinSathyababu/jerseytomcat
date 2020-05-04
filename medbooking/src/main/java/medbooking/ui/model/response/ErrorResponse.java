package medbooking.ui.model.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {

	private String errorMessage;
	private String errorMessageCode;
	private String href;
	
	public ErrorResponse() {
		super();
	}
	public ErrorResponse(String errorMessage, String errorMessageCode, String href) {
		super();
		this.errorMessage = errorMessage;
		this.errorMessageCode = errorMessageCode;
		this.href = href;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessageCode() {
		return errorMessageCode;
	}
	public void setErrorMessageCode(String errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	
}
