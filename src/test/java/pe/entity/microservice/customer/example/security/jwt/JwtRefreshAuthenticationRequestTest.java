package pe.entity.microservice.customer.example.security.jwt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Jwt Refresh Authentication Request Test
 * @author lpazd
 *
 */
public class JwtRefreshAuthenticationRequestTest {

	@InjectMocks
	JwtRefreshAuthenticationRequest jwtRefreshAuthenticationRequest;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		jwtRefreshAuthenticationRequest = new JwtRefreshAuthenticationRequest();
		jwtRefreshAuthenticationRequest = new JwtRefreshAuthenticationRequest("token");
	}

	@Test
	public void whenGetSuccess_ThenReturnSuccess() {
		jwtRefreshAuthenticationRequest.setToken("token");
		Assert.assertNotNull(jwtRefreshAuthenticationRequest.getToken());
	}
}
