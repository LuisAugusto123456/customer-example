package pe.entity.microservice.customer.example.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Customer Request Object Test
 * 
 * @author lpazd
 *
 */
public class CustomerRequestObjectTest {

	@InjectMocks
	CustomerRequestObject customerRequestObject;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		customerRequestObject = new CustomerRequestObject("userName", "password", "firstName", "lastName",
				Integer.valueOf("32"), "birthDate");
	}

	@Test
	public void whenSetAndGet_thenReturnSuccess() {
		customerRequestObject.setPassword("password");
		customerRequestObject.setUserName("userName");
		customerRequestObject.setAge(Integer.valueOf("32"));
		customerRequestObject.setBirthDate("birthDate");
		customerRequestObject.setFirstName("firstName");
		customerRequestObject.setLastName("lastName");
		Assert.assertNotNull(customerRequestObject.getPassword());
		Assert.assertNotNull(customerRequestObject.getUserName());
		Assert.assertNotNull(customerRequestObject.getBirthDate());
		Assert.assertNotNull(customerRequestObject.getFirstName());
		Assert.assertNotNull(customerRequestObject.getLastName());
		Assert.assertNotNull(customerRequestObject.getAge());
	}
}
