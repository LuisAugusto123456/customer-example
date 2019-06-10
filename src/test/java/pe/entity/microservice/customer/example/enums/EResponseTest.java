package pe.entity.microservice.customer.example.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Enum Response Test
 * 
 * @author lpazd
 *
 */
public class EResponseTest {

	@Test
	public void testEnumValues() {
		Assert.assertNotNull(EResponse.values());
	}

	@Test
	public void testEnumValue_NonSpecific() {
		Assert.assertNotNull(EResponse.NON_SPECIFIC.getCode());
	}

	@Test
	public void testEnumValue_UserNameExist() {
		Assert.assertNotNull(EResponse.USER_NAME_EXIST.getCode());
	}
}
