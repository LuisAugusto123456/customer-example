package pe.entity.microservice.customer.example.security.jwt;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jwt Authentication Request
 * 
 * @author lpazd
 *
 */
public class JwtAuthenticationRequest implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -8445943548965154778L;

	/**
	 * List claims
	 */
	private List<Claim> claims;

	/**
	 * id
	 */
	private String id;

	/**
	 * Jwt Authentication Request
	 */
	public JwtAuthenticationRequest() {
		super();
	}

	/**
	 * Jwt Authentication Request
	 * 
	 * @param claims claims
	 * @param id     id
	 */
	public JwtAuthenticationRequest(@JsonProperty("claims") List<Claim> claims, @JsonProperty("id") String id) {
		this.claims = claims;
		this.id = id;
	}

	/**
	 * Get Claims
	 * 
	 * @return claims
	 */
	public List<Claim> getClaims() {
		return claims;
	}

	/**
	 * Set Claims
	 * 
	 * @param claims claims
	 */
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id
	 * 
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
