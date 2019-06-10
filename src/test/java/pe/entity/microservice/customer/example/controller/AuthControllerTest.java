package pe.entity.microservice.customer.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;
import pe.entity.microservice.customer.example.bo.AuthBO;
import pe.entity.microservice.customer.example.request.SignRequestObject;
import pe.entity.microservice.customer.example.security.jwt.JwtRefreshAuthenticationRequest;

/**
 * Auth Controller Test
 * 
 * @author lpazd
 *
 */
public class AuthControllerTest {

	@InjectMocks
	@Spy
	private AuthController authController;

	@Mock
	private AuthBO authBO;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenSignIn_thenReturnSuccess() {
		Mockito.when(authBO.signIn(Mockito.anyString(), Mockito.anyString())).thenReturn(ResponseEntity.ok().build());
		authController.signIn(new SignRequestObject("userName", "password"));
	}

	@Test
	public void whenRefreshAndGetAuthenticationToken_thenReturnSuccess() {
		Mockito.when(authBO.refreshAndGetAuthenticationToken(Mockito.any())).thenReturn(ResponseEntity.ok().build());
		authController.refreshToken(new JwtRefreshAuthenticationRequest("token"));
	}
}
