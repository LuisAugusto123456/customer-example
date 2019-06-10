package pe.entity.microservice.customer.example.service;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pe.entity.microservice.customer.example.model.User;
import pe.entity.microservice.customer.example.repository.UserRepository;

/**
 * User Service Test
 * 
 * @author lpazd
 *
 */
public class UserServiceTest {

	@InjectMocks
	@Spy
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenValidateServicesSuccess_ThenReturnSuccess() {
		User user = new User(1L, "uuid", "userName", "password", "firstName", "lastName", 32, new Date(), "createdUser",
				new Date(), "lastModifiedUser", new Date());
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Assert.assertNotNull(userServiceImpl.save(user));
		Mockito.when(userRepository.findByUuid("uuid")).thenReturn(user);
		Assert.assertNotNull(userServiceImpl.findByUuid("uuid"));
		Mockito.when(userRepository.findByUserName("userName")).thenReturn(user);
		Assert.assertNotNull(userServiceImpl.findByUserName("userName"));
		Mockito.when(userRepository.findByUserNameAndPassword("userName", "password")).thenReturn(user);
		Assert.assertNotNull(userServiceImpl.findByUserNameAndPassword("userName", "password"));
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		Assert.assertNotNull(userServiceImpl.findAll());
	}
}
