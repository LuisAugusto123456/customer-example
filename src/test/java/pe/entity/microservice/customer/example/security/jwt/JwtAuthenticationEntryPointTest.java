package pe.entity.microservice.customer.example.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Jwt Authentication Entry Point Test
 * @author lpazd
 *
 */
public class JwtAuthenticationEntryPointTest {

	@InjectMocks
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Mock
	HttpServletResponse httpServletResponse;
	
	@Mock
	PrintWriter printWriter;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenCommenceSuccess_ThenReturnSuccess() throws IOException, InstantiationException, IllegalAccessException {
		Mockito.when(httpServletResponse.getWriter()).thenReturn(printWriter);
		jwtAuthenticationEntryPoint.commence(null, httpServletResponse, null);
	}
}
