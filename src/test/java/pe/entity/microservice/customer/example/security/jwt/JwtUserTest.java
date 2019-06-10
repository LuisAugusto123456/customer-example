package pe.entity.microservice.customer.example.security.jwt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;

/**
 * Jwt User Test
 * 
 * @author lpazd
 *
 */
public class JwtUserTest {

	@InjectMocks
	JwtUser jwtUser;

	GrantedAuthority grantedAuthority;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		jwtUser = new JwtUser(1L, "Usuario Autorizado", null);
	}

	@Test
	public void whenGetSuccess_ThenReturnSuccess() {
		Assert.assertNotNull(jwtUser.getPassword());
		Assert.assertNotNull(jwtUser.getUsername());
		Assert.assertNull(jwtUser.getAuthorities());
		Assert.assertNotNull(jwtUser.getId());	
		Assert.assertTrue(jwtUser.isAccountNonExpired());
		Assert.assertTrue(jwtUser.isAccountNonLocked());
		Assert.assertTrue(jwtUser.isCredentialsNonExpired());
		Assert.assertTrue(jwtUser.isEnabled());
	}

}
