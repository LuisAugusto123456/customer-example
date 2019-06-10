package pe.entity.microservice.customer.example.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Sign Request Object
 * 
 * @author lpazd
 *
 */
@JsonInclude(Include.NON_NULL)
public class SignRequestObject {

	/**
	 * user name
	 */
	@JsonProperty("user_name")
	private String userName;

	/**
	 * password
	 */
	@JsonProperty("password")
	private String password;

	/**
	 * Sign Request Object
	 * 
	 * @param userName user name
	 * @param password password
	 */
	public SignRequestObject(@JsonProperty("user_name") String userName, @JsonProperty("password") String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Get User Name
	 * 
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set User Name
	 * 
	 * @param userName user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get Password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set Password
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
