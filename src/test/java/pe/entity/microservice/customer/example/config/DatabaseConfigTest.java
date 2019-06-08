package pe.entity.microservice.customer.example.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;
import pe.entity.microservice.customer.example.exception.GenericException;

/**
 * Database Config Test
 * 
 * @author lpazd
 *
 */
public class DatabaseConfigTest {

	@InjectMocks
	@Spy
	private DatabaseConfig databaseConfig;

	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(databaseConfig, "dbUrl", "jdbc:mysql://localhost:3306/dev");
		ReflectionTestUtils.setField(databaseConfig, "dbUsername", "root");
		ReflectionTestUtils.setField(databaseConfig, "dbPassword", "Peru123.");
		ReflectionTestUtils.setField(databaseConfig, "resourceDir", "/data/customer-example/properties");
		ReflectionTestUtils.setField(databaseConfig, "connectionTimeout", 5000);
		ReflectionTestUtils.setField(databaseConfig, "connectionIdleTimeout", 60000);
		ReflectionTestUtils.setField(databaseConfig, "initializationFailTimeout", 5);
		ReflectionTestUtils.setField(databaseConfig, "connectionMaxLifetime", 3600000);
		ReflectionTestUtils.setField(databaseConfig, "validationTimeout", 300);
		ReflectionTestUtils.setField(databaseConfig, "minimumIdle", 2);
		ReflectionTestUtils.setField(databaseConfig, "networkProtocol", "TCP");
		ReflectionTestUtils.setField(databaseConfig, "comMysqlJdbcDriver", "com.mysql.jdbc.Driver");
		ReflectionTestUtils.setField(databaseConfig, "connectionLoginTimeOut", 3);
		ReflectionTestUtils.setField(databaseConfig, "connectionMaxLimit", 10);
		ReflectionTestUtils.setField(databaseConfig, "hibernateCache", "true");
		ReflectionTestUtils.setField(databaseConfig, "hibernateDialect", "org.hibernate.dialect.MySQLDialect");
		ReflectionTestUtils.setField(databaseConfig, "maxFetchDepth", "3");
		ReflectionTestUtils.setField(databaseConfig, "fetchSize", "10");
		ReflectionTestUtils.setField(databaseConfig, "batchSize", "30");
		ReflectionTestUtils.setField(databaseConfig, "flushMode", "COMMIT");
		ReflectionTestUtils.setField(databaseConfig, "showSql", "false");
		ReflectionTestUtils.setField(databaseConfig, "hibernateQueryCache", "");
	}

	@Test
	public void whenTestBean_thenReturnSuccess() throws GenericException {
		Assert.assertNotNull(databaseConfig.entityManager());
		Assert.assertNotNull(databaseConfig.entityManagerFactory());
		Assert.assertNotNull(databaseConfig.transactionManager());
		Assert.assertNotNull(databaseConfig.jdbcTemplate(databaseConfig.dataSource()));
	}
}
