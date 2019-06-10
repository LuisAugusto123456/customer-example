package pe.entity.microservice.customer.example.security.jwt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * Jwt User Factory Test
 * @author lpazd
 *
 */
public class JwtUserFactoryTest {
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenCreateSuccess_ThenReturnSuccess() {
		Assert.assertNotNull(JwtUserFactory.create());
	}
}
