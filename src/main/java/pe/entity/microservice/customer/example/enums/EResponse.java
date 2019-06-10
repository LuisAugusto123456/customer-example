package pe.entity.microservice.customer.example.enums;

/**
 * Enum response
 * 
 * @author lpazd
 *
 */
public enum EResponse {

	NON_SPECIFIC(1010, "Ups! Ha ocurrido un error."), USER_NAME_EXIST(1011, "User name ya se encuentra registrado.");

	/**
	 * code
	 */
	private final int code;

	/**
	 * key message
	 */
	private final String keyMessage;

	/**
	 * Enum Response
	 * 
	 * @param code       code
	 * @param keyMessage key message
	 */
	EResponse(int code, String keyMessage) {
		this.code = code;
		this.keyMessage = keyMessage;
	}

	/**
	 * Get Code
	 * 
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Get Key Message
	 * 
	 * @return key message
	 */
	public String getKeyMessage() {
		return keyMessage;
	}
}
