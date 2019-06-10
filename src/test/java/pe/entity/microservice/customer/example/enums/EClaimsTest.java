package pe.entity.microservice.customer.example.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Enum Claims Test
 * 
 * @author lpazd
 *
 */
public class EClaimsTest {

	@Test
	public void testEnumValues() {
		Assert.assertNotNull(EClaims.values());
	}

	@Test
	public void testEnumValue_Id() {
		Assert.assertNotNull(EClaims.ID.getCode());
	}

	@Test
	public void testEnumValue_Name() {
		Assert.assertNotNull(EClaims.NAME.getCode());
	}

	@Test
	public void testEnumValue_Gen() {
		Assert.assertNotNull(EClaims.GEN.getCode());
	}

	@Test
	public void testEnumValue_Sub() {
		Assert.assertNotNull(EClaims.SUB.getCode());
	}

	@Test
	public void testEnumValue_Created() {
		Assert.assertNotNull(EClaims.CREATED.getCode());
	}
}
