package pe.entity.microservice.customer.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pe.entity.microservice.customer.example.CustomerExampleServer.ConfigDev;

/**
 * Customer Example Server Test
 * @author lpazd
 *
 */
public class CustomerExampleServerTest {

	@InjectMocks
	CustomerExampleServer customerExampleServer;

	@InjectMocks
	ConfigDev configDev;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void beansTest() {
		Assert.assertNotNull(configDev.init());
	}

}
