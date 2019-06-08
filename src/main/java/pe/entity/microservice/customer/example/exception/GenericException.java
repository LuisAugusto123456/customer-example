package pe.entity.microservice.customer.example.exception;

/**
 * Class Generic Exception
 * 
 * @author lpazd
 *
 */
public class GenericException extends Exception {

	/**
	 * code
	 */
	private final Integer code;

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor Generic Exception
	 * 
	 * @param e throwable
	 */
	public GenericException(Throwable e) {
		super(e);
		code = -1;
	}

	/**
	 * Constructor Generic Exception
	 * 
	 * @param message message
	 */
	public GenericException(String message) {
		super(message);
		code = -1;
	}

	/**
	 * Constructor Generic Exception
	 * 
	 * @param message message
	 * @param e       throwable
	 */
	public GenericException(String message, Throwable e) {
		super(message, e);
		code = -1;
	}

	/**
	 * Constructor Generic Exception
	 * 
	 * @param code    code
	 * @param message message
	 * @param e       throwable
	 */
	public GenericException(Integer code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}

	/**
	 * Constructor Generic Exception
	 * 
	 * @param code    code
	 * @param message message
	 */
	public GenericException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Get Code
	 * 
	 * @return code
	 */
	public Integer getCode() {
		return code;
	}

}
