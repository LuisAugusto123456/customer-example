package pe.entity.microservice.customer.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generic Response Object
 * 
 * @author lpazd
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponseObject {

	/**
	 * code
	 */
	@JsonProperty("code")
	private int code;

	/**
	 * message
	 */
	@JsonProperty("message")
	private String message;

	/**
	 * Get Code
	 * 
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Set Code
	 * 
	 * @param code code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Get Message
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set Message
	 * 
	 * @param message message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Generic Response Object
	 */
	public GenericResponseObject() {

	}

	/**
	 * Generic Response Object
	 * 
	 * @param code    code
	 * @param message message
	 */
	public GenericResponseObject(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
