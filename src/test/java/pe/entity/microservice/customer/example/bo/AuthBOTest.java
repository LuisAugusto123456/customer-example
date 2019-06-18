package pe.entity.microservice.customer.example.bo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import pe.entity.microservice.customer.example.enums.EClaims;
import pe.entity.microservice.customer.example.model.User;
import pe.entity.microservice.customer.example.security.jwt.JwtRefreshAuthenticationRequest;
import pe.entity.microservice.customer.example.security.jwt.JwtUtil;
import pe.entity.microservice.customer.example.service.UserService;

/**
 * Auth BO Test
 * @author lpazd
 *
 */
public class AuthBOTest {

	@InjectMocks
	@Spy
	private AuthBO authBO;
	
	@Mock
	private UserService userService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(authBO, "secret", "3fc68991cb0ccc2f0cad25d4a4317b2cb58e881d", String.class);
		JwtUtil.setSecret("3fc68991cb0ccc2f0cad25d4a4317b2cb58e881d");
		JwtUtil.setExpirationAccessToken(Long.valueOf(120));
		JwtUtil.setExpirationRefreshToken(Long.valueOf(300));
	}
	
	@Test
	public void whenSignIn_thenReturnSuccess() throws ParseException {
		User user = new User("uuid", "userName", "password", "firstName", "lastName", 32,
				new SimpleDateFormat("dd/MM/yyyy").parse("13/04/1987"), "createdUser", new Date(), "lastModifiedUser",
				new Date());
		Mockito.when(userService.findByUserNameAndPassword("userName","password")).thenReturn(user);
		authBO.signIn("userName", "password");
	}
	
	@Test
	public void whenSignIn_thenReturnErrorCredential() throws ParseException {
		Mockito.when(userService.findByUserNameAndPassword("userName","password")).thenReturn(null);
		authBO.signIn("userName", "password");
	}
	
	@Test
	public void whenRefreshAndGetAuthenticationToken_thenReturnSuccess() throws ParseException, IOException {
		Claims claims = Jwts.claims();
		claims.put(EClaims.GEN.getCode(), 1);
		claims.put(EClaims.ID.getCode(), 1);
		authBO.refreshAndGetAuthenticationToken(new JwtRefreshAuthenticationRequest("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1MDAwMDAxNjgwIiwiYWNjZXNzIjoibW9iaWxlIiwiYXBwbGljYXRpb24iOiIxIiwiY3JlYXRlZCI6MTU1NjI5MjExMjUxMSwiaWQiOiI1MDAwMDAxNjgwIiwiZXhwIjoyMTc3NDcwNzk5LCJwbGF0Zm9ybSI6IjEiLCJjb21tZXJjZUlkIjpudWxsLCJkZXZpY2VUb2tlbiI6ImNpZ29YSmxWclY4OkFQQTkxYkgxM3RIeC1mOEVaZ0xhR3dYSUJjV1labGE3LW5XOGRRQk9rU21GbTJvcEdZdmstR2NzTGUyc2hWb0hodFlSZ1M1ZVRfeERGR09kZmRQVFZTbWhqdVliQzlyRmZSZ0NYYWc2cHd5aFNsUm1FWk9fcVdJajFNN0l1bzQyQmMtam9PQmI0WjNDIn0.-Vroqj9hkWn_C0pyjdgL2dVuBilNM55rE6OKQCZcxQdlPuAC7-pO43q2RT6EVACIPTlsvbRl9Iqrq5gZ7OouDA"));
	}
	
	@Test
	public void whenRefreshAndGetAuthenticationToken_thenReturnErrorGen() throws ParseException, IOException {
		Claims claims = Jwts.claims();
		claims.put(EClaims.ID.getCode(), 1);
		authBO.refreshAndGetAuthenticationToken(new JwtRefreshAuthenticationRequest("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1MDAwMDAxNjgwIiwiYWNjZXNzIjoibW9iaWxlIiwiYXBwbGljYXRpb24iOiIxIiwiY3JlYXRlZCI6MTU1NjI5MjExMjUxMSwiaWQiOiI1MDAwMDAxNjgwIiwiZXhwIjoyMTc3NDcwNzk5LCJwbGF0Zm9ybSI6IjEiLCJjb21tZXJjZUlkIjpudWxsLCJkZXZpY2VUb2tlbiI6ImNpZ29YSmxWclY4OkFQQTkxYkgxM3RIeC1mOEVaZ0xhR3dYSUJjV1labGE3LW5XOGRRQk9rU21GbTJvcEdZdmstR2NzTGUyc2hWb0hodFlSZ1M1ZVRfeERGR09kZmRQVFZTbWhqdVliQzlyRmZSZ0NYYWc2cHd5aFNsUm1FWk9fcVdJajFNN0l1bzQyQmMtam9PQmI0WjNDIn0.-Vroqj9hkWn_C0pyjdgL2dVuBilNM55rE6OKQCZcxQdlPuAC7-pO43q2RT6EVACIPTlsvbRl9Iqrq5gZ7OouDA"));
	}
}
