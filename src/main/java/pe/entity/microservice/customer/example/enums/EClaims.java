package pe.entity.microservice.customer.example.enums;

/**
 * Enum Claims
 * 
 * @author lpazd
 *
 */
public enum EClaims {
	ID("id"), NAME("name"), GEN("gen"), CREATED("created"), SUB("sub");

	/**
	 * code
	 */
	private final String code;

	/**
	 * Enum Claims
	 * 
	 * @param code code
	 */
	EClaims(String code) {
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
