package pe.entity.microservice.customer.example.response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Generic Response Object Test
 * 
 * @author lpazd
 *
 */
public class GenericResponseObjectTest {

	@InjectMocks
	GenericResponseObject genericResponseObject;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		genericResponseObject = new GenericResponseObject(1, "message");
	}

	@Test
	public void whenSetAndGet_thenReturnSuccess() {
		genericResponseObject.setCode(2);
		genericResponseObject.setMessage("message");
		Assert.assertNotNull(genericResponseObject.getCode());
		Assert.assertNotNull(genericResponseObject.getMessage());
	}
}
