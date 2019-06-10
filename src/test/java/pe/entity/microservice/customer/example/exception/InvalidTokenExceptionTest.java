package pe.entity.microservice.customer.example.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class InvalidTokenExceptionTest {

	@InjectMocks
	InvalidTokenException invalidTokenException;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		invalidTokenException = new InvalidTokenException();
		
	}
	
	@Test
	public void whenGet_thenReturnSuccess() {
		Assert.assertNotNull(invalidTokenException);
	}
}
