package pe.entity.microservice.customer.example.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Jwt Authentication Token Filter Test
 * 
 * @author lpazd
 *
 */
public class JwtAuthenticationTokenFilterTest {

	@InjectMocks
	JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	
	@Mock
	HttpServletRequest httpServletRequest;
	
	@Mock
	HttpServletResponse httpServletResponse;
	
	@Mock
	FilterChain filterChain;
	
	@Mock
	UserDetailsService userDetailsService;

	@Mock
	UserDetails userDetails;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenLoadUserByUsernameSuccess_ThenReturnSuccess() throws ServletException, IOException {
		Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);
		jwtAuthenticationTokenFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
	}
}
