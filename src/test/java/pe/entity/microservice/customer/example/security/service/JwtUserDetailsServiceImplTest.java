package pe.entity.microservice.customer.example.security.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Jwt User Details Service Impl Test
 * 
 * @author lpazd
 *
 */
public class JwtUserDetailsServiceImplTest {

	@InjectMocks
	JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenLoadUserByUsernameSuccess_ThenReturnSuccess() {
		Assert.assertNotNull(jwtUserDetailsServiceImpl.loadUserByUsername("username"));
	}
}
