package pe.entity.microservice.customer.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;
import pe.entity.microservice.customer.example.bo.UserBO;
import pe.entity.microservice.customer.example.request.CustomerRequestObject;

/**
 * User Controller Test
 * 
 * @author lpazd
 *
 */
public class UserControllerTest {

	@InjectMocks
	@Spy
	private UserController userController;

	@Mock
	private UserBO userBO;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenSaveUser_thenReturnSuccess() {
		Mockito.when(userBO.saveUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt(), Mockito.anyString())).thenReturn(ResponseEntity.ok().build());
		userController.saveUser(new CustomerRequestObject("userName", "password", "firstName", "lastName",
				Integer.valueOf("1"), "birthDate"));
	}

	@Test
	public void whenKpi_thenReturnSuccess() {
		Mockito.when(userBO.kpi()).thenReturn(ResponseEntity.ok().build());
		userController.kpi();
	}

	@Test
	public void whenCustomerList_thenReturnSuccess() {
		Mockito.when(userBO.customerList()).thenReturn(ResponseEntity.ok().build());
		userController.customerList();
	}
}
