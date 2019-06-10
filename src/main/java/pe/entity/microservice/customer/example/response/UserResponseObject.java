package pe.entity.microservice.customer.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User Response Object
 * 
 * @author lpazd
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseObject {

	/**
	 * user uuid
	 */
	@JsonProperty("user_uuid")
	private String userUuid;

	/**
	 * access token
	 */
	@JsonProperty("access_token")
	private String accessToken;

	/**
	 * refresh token
	 */
	@JsonProperty("refresh_token")
	private String refreshToken;

	/**
	 * User Response Object
	 * 
	 * @param userUuid     user uuid
	 * @param accessToken  access token
	 * @param refreshToken refresh token
	 */
	public UserResponseObject(String userUuid, String accessToken, String refreshToken) {
		this.userUuid = userUuid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	/**
	 * Get User Uuid
	 * 
	 * @return uuid
	 */
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * Set User Uuid
	 * 
	 * @param userUuid uuid
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/**
	 * Get Access Token
	 * 
	 * @return access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Set Access Token
	 * 
	 * @param accessToken access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Get Refresh Token
	 * 
	 * @return refresh token
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Set Refresh Token
	 * 
	 * @param refreshToken refresh token
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
