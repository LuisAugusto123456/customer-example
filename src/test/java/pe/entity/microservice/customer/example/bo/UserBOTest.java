package pe.entity.microservice.customer.example.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pe.entity.microservice.customer.example.enums.EClaims;
import pe.entity.microservice.customer.example.model.User;
import pe.entity.microservice.customer.example.security.jwt.Claim;
import pe.entity.microservice.customer.example.security.jwt.JwtAuthenticationRequest;
import pe.entity.microservice.customer.example.security.jwt.JwtUtil;
import pe.entity.microservice.customer.example.service.UserService;

/**
 * User BO Test
 * 
 * @author lpazd
 *
 */
public class UserBOTest {

	@InjectMocks
	@Spy
	private UserBO userBO;

	@Mock
	private UserService userService;

	@Mock
	private AuthBO authBO;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		List<Claim> claims = new ArrayList<>();
		claims.add(new Claim(EClaims.NAME.getCode(), "userName"));
		Mockito.when(authBO.getJwtAuthenticationRequest(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(new JwtAuthenticationRequest(claims, "uuid"));
		JwtUtil.setSecret("3fc68991cb0ccc2f0cad25d4a4317b2cb58e881d");
		JwtUtil.setExpirationAccessToken(Long.valueOf(120));
		JwtUtil.setExpirationRefreshToken(Long.valueOf(300));
	}

	@Test
	public void whenSaveUser_thenReturnSuccess() throws ParseException {
		User user = new User("uuid", "userName", "password", "firstName", "lastName", 32,
				new SimpleDateFormat("dd/MM/yyyy").parse("13/04/1987"), "createdUser", new Date(), "lastModifiedUser",
				new Date());
		Mockito.when(userService.findByUserName("userName")).thenReturn(null);
		Mockito.when(userService.save(Mockito.any())).thenReturn(user);
		userBO.saveUser("userName", "password", "firstName", "lastName", 32, "13/04/1987");
	}

	@Test
	public void whenSaveUser_thenReturnError_userNameExist() throws ParseException {
		User user = new User("uuid", "userName", "password", "firstName", "lastName", 32,
				new SimpleDateFormat("dd/MM/yyyy").parse("13/04/1987"), "createdUser", new Date(), "lastModifiedUser",
				new Date());
		Mockito.when(userService.findByUserName("userName")).thenReturn(user);
		userBO.saveUser("userName", "password", "firstName", "lastName", 32, "13/04/1987");
	}

	@Test
	public void whenKPI_thenReturnSuccess() {
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(new User(null, null, null, null, null, 12, null, null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 55, null, null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 74, null, null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 79, null, null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 90, null, null, null, null, null));
		Mockito.when(userService.findAll()).thenReturn(lstUser);
		userBO.kpi();
	}

	@Test
	public void whenKPI_thenReturnError_list() {
		Mockito.when(userService.findAll()).thenReturn(null);
		userBO.kpi();
	}
	
	@Test
	public void whenCustomerList_thenReturnSuccess() throws ParseException {
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(new User(null, null, null, null, null, 12, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2007"), null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 55, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1964"), null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 74, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1945"), null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 79, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1940"), null, null, null, null));
		lstUser.add(new User(null, null, null, null, null, 90, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1929"), null, null, null, null));
		Mockito.when(userService.findAll()).thenReturn(lstUser);
		userBO.customerList();
	}
	
	@Test
	public void whenCustomerList_thenReturnError_list() {
		Mockito.when(userService.findAll()).thenReturn(null);
		userBO.customerList();
	}
}
