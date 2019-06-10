package pe.entity.microservice.customer.example.response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * User Response Object Test
 * 
 * @author lpazd
 *
 */
public class UserResponseObjectTest {

	@InjectMocks
	UserResponseObject userResponseObject;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userResponseObject = new UserResponseObject("userUuid", "accessToken", "refreshToken");
	}

	@Test
	public void whenSetAndGet_thenReturnSuccess() {
		userResponseObject.setAccessToken("accessToken");
		userResponseObject.setRefreshToken("refreshToken");
		userResponseObject.setUserUuid("userUuid");
		Assert.assertNotNull(userResponseObject.getAccessToken());
		Assert.assertNotNull(userResponseObject.getRefreshToken());
		Assert.assertNotNull(userResponseObject.getUserUuid());
	}
}
