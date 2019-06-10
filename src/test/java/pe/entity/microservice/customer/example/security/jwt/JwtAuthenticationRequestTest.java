package pe.entity.microservice.customer.example.security.jwt;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Jwt Authentication Request Test
 * 
 * @author lpazd
 *
 */
public class JwtAuthenticationRequestTest {

	@InjectMocks
	JwtAuthenticationRequest jwtAuthenticationRequest;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		jwtAuthenticationRequest = new JwtAuthenticationRequest();
		jwtAuthenticationRequest = new JwtAuthenticationRequest(null, null);
	}

	@Test
	public void whenGetSuccess_ThenReturnSuccess() {
		jwtAuthenticationRequest.setClaims(new ArrayList<Claim>());
		jwtAuthenticationRequest.setId("id");
		Assert.assertNotNull(jwtAuthenticationRequest.getId());
		Assert.assertNotNull(jwtAuthenticationRequest.getClaims());
	}
}
