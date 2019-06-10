package pe.entity.microservice.customer.example.security.jwt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Claim Test
 * 
 * @author lpazd
 *
 */
public class ClaimTest {

	@InjectMocks
	Claim claim;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		claim = new Claim();
		claim = new Claim("key", "value");
	}

	@Test
	public void whenGetSuccess_ThenReturnSuccess() {
		claim.setKey("key");
		claim.setValue("value");
		Assert.assertNotNull(claim.getKey());
		Assert.assertNotNull(claim.getValue());
	}
}
