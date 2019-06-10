package pe.entity.microservice.customer.example.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Enum Request Context Test
 * 
 * @author lpazd
 *
 */
public class ERequestContextTest {

	@Test
	public void testEnumValues() {
		Assert.assertNotNull(ERequestContext.values());
	}

	@Test
	public void testEnumValue_Claims() {
		Assert.assertNotNull(ERequestContext.CLAIMS.getCode());
	}
}
