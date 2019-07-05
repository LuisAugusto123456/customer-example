package pe.entity.microservice.customer.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;

import pe.entity.microservice.customer.example.bo.ExampleBO;

/**
 * Example Controller Test
 * 
 * @author lpazd
 *
 */
public class ExampleControllerTest {

	@InjectMocks
	@Spy
	private ExampleController exampleController;

	@Mock
	private ExampleBO exampleBO;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenExampleFlux_thenReturnSuccess() {
		Mockito.when(exampleBO.exampleFlux()).thenReturn(ResponseEntity.ok().build());
		exampleController.exampleFlux();
	}
}
