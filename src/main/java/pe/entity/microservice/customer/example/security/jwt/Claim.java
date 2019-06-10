package pe.entity.microservice.customer.example.security.jwt;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author lpazd
 *
 */
public class Claim implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -8445943548965154778L;

	/**
	 * key
	 */
	private String key;

	/**
	 * value
	 */
	private String value;

	/**
	 * claim
	 */
	public Claim() {

	}

	/**
	 * claim
	 * 
	 * @param key   key
	 * @param value value
	 */
	public Claim(@JsonProperty("key") String key, @JsonProperty("value") String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Get Key
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Set key
	 * 
	 * @param key key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Get value
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set value
	 * 
	 * @param value value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
