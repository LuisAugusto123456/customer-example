package pe.entity.microservice.customer.example.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * SignRequest Object Test
 * 
 * @author lpazd
 *
 */
public class SignRequestObjectTest {

	@InjectMocks
	SignRequestObject signRequestObject;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		signRequestObject = new SignRequestObject("userName", "password");
	}

	@Test
	public void whenSetAndGet_thenReturnSuccess() {
		signRequestObject.setPassword("password");
		signRequestObject.setUserName("userName");
		Assert.assertNotNull(signRequestObject.getPassword());
		Assert.assertNotNull(signRequestObject.getUserName());
	}
}
