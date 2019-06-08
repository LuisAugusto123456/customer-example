package pe.entity.microservice.customer.example.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Generic Exception Test
 * 
 * @author lpazd
 *
 */
public class GenericExceptionTest {

	@InjectMocks
	GenericException genericException;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		Throwable e = new Throwable();
		genericException = new GenericException("message");
		genericException = new GenericException(e);
		genericException = new GenericException(1, "message");
		genericException = new GenericException("message", e);
		genericException = new GenericException(1, "message", e);
	}
	
	@Test
	public void whenGet_thenReturnSuccess() {
		Assert.assertNotNull(genericException.getCode());
	}
}
