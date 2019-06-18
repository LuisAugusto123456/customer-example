package pe.entity.microservice.customer.example.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Sign Request Object
 * 
 * @author lpazd
 *
 */
@ApiModel(description = "Class representing a sign in.")
@JsonInclude(Include.NON_NULL)
public class SignRequestObject implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -8445943548965154778L;

	/**
	 * user name
	 */
	@ApiModelProperty(notes = "User name the client", example = "lpazd", required = true, position = 0)
	@JsonProperty("user_name")
	private String userName;

	/**
	 * password
	 */
	@ApiModelProperty(notes = "Password the client", example = "123456", required = true, position = 1)
	@JsonProperty("password")
	private String password;

	/**
	 * Sign Request Object
	 */
	public SignRequestObject() {
		super();
	}

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
