package pe.entity.microservice.customer.example.security.jwt;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Jwt Refresh Authentication Request
 * 
 * @author lpazd
 *
 */
@ApiModel(description = "Class representing a token.")
public class JwtRefreshAuthenticationRequest implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -8445943548965154778L;

	/**
	 * token
	 */
	@ApiModelProperty(notes = "token the client", example = "xxxxxxxxxx", required = true, position = 0)
	private String token;

	/**
	 * Jwt Refresh Authentication Request
	 */
	public JwtRefreshAuthenticationRequest() {
		super();
	}

	/**
	 * Jwt Refresh Authentication Request
	 * 
	 * @param token token
	 */
	public JwtRefreshAuthenticationRequest(@JsonProperty("token") String token) {
		this.token = token;
	}

	/**
	 * Get token
	 * 
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Set token
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
