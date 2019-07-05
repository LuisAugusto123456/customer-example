package pe.entity.microservice.customer.example.bo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ExampleBOTest {

	@InjectMocks
	@Spy
	private ExampleBO exampleBO;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenExampleFlux_thenReturnSuccess() {
		exampleBO.exampleFlux();
	}
	
	@Test
	public void whenExampleRxJava2_thenReturnSuccess() {
		exampleBO.exampleRxJava2();
	}
	
	@Test
	public void whenExampleTickTock_thenReturnSuccess() {
		exampleBO.exampleTickTock();
	}
	
	@Test
	public void whenExamplePriceTickLauncher_thenReturnSuccess() {
		exampleBO.examplePriceTickLauncher();
	}
	
	@Test
	public void whenExampleRxJava2_v2_thenReturnSuccess() {
		exampleBO.exampleRxJava2_v2();
	}
	
	@Test
	public void whenExampleRxJava2_v3_thenReturnSuccess() {
		exampleBO.exampleRxJava2_v3();
	}
	
	@Test
	public void whenExampleRxJava2_v4_thenReturnSuccess() {
		exampleBO.exampleRxJava2_v4();
	}
	
	@Test
	public void whenExampleRxJava2_v5_thenReturnSuccess() {
		exampleBO.exampleRxJava2_v5();
	}
	
	@Test
	public void whenExampleRxJava2_v6_thenReturnSuccess() {
		exampleBO.exampleRxJava2_v6();
	}
	
	@Test
	public void whenExampleRxJava2_v7_thenReturnSuccess() {
		exampleBO.exampleRxJava2_v7();
	}
}
