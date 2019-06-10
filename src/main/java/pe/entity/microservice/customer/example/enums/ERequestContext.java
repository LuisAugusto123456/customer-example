package pe.entity.microservice.customer.example.enums;

/**
 * Enum Request Context
 * 
 * @author lpazd
 *
 */
public enum ERequestContext {
	CLAIMS("CLAIMS");

	/**
	 * code
	 */
	private final String code;

	/**
	 * Enum Request Context
	 * 
	 * @param code code
	 */
	ERequestContext(String code) {
		this.code = code;
	}

	/**
	 * Get code
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}
}
