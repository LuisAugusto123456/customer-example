package pe.entity.microservice.customer.example.response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class KpiResponseObjectTest {

	@InjectMocks
	KpiResponseObject kpiResponseObject;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		kpiResponseObject = new KpiResponseObject(1, 1);
	}

	@Test
	public void whenSetAndGet_thenReturnSuccess() {
		kpiResponseObject.setAverage(2);
		kpiResponseObject.setStandardDeviation(2);
		Assert.assertNotNull(kpiResponseObject.getAverage());
		Assert.assertNotNull(kpiResponseObject.getStandardDeviation());
	}
}
