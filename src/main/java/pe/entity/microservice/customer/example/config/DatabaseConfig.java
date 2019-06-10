package pe.entity.microservice.customer.example.config;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import pe.entity.microservice.customer.example.exception.GenericException;

/**
 * Class Database Config
 * 
 * @author lpazd
 *
 */
@Configuration
@Profile({ "dev", "uat", "prod" })
@PropertySource({ "${propertiesDir}/${envTarget:dev}/config/database-${envTarget:dev}.properties" })
@PropertySource({ "${propertiesDir}/${envTarget:dev}/secrets/database-${envTarget:dev}.properties" })
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"pe.entity.microservice.customer.example.repository" })
@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class,
		DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableTransactionManagement
public class DatabaseConfig {

	/**
	 * logger database config
	 */
	private static final Logger LOGGER_DATABASECONFIG = LogManager.getLogger(DatabaseConfig.class);

	/**
	 * password
	 */
	@Value("${jdbc.password}")
	private String dbPassword;

	/**
	 * url
	 */
	@Value("${jdbc.url}")
	private String dbUrl;

	/**
	 * user name
	 */
	@Value("${jdbc.username}")
	private String dbUsername;

	/**
	 * hibernate dialect
	 */
	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	/**
	 * hibernate cahe
	 */
	@Value("${hibernate.cache}")
	private String hibernateCache;

	/**
	 * resource dir
	 */
	@Value("${app.resources.location}")
	private String resourceDir;

	/**
	 * connection login timeOut
	 */
	@Value("${db.connection.login.timeout}")
	private int connectionLoginTimeOut;

	/**
	 * network protocol
	 */
	@Value("${network.protocol}")
	private String networkProtocol;

	/**
	 * hibernate max fetch depth
	 */
	@Value("${hibernate.max_fetch_depth}")
	private String hibernateMaxFetchDepth;

	/**
	 * max fetch depth
	 */
	@Value("${hibernate.max_fetch_depth}")
	private String maxFetchDepth;

	/**
	 * fetch size
	 */
	@Value("${hibernate.jdbc.fetch_size}")
	private String fetchSize;

	/**
	 * hibernate query cache
	 */
	@Value("${hibernate.query.cache}")
	private String hibernateQueryCache;

	/**
	 * batch size
	 */
	@Value("${hibernate.jdbc.batch_size}")
	private String batchSize;

	/**
	 * show sql
	 */
	@Value("${hibernate.show_sql}")
	private String showSql;

	/**
	 * format sql
	 */
	@Value("${hibernate.format_sql}")
	private String formatSql;

	/**
	 * flush mode
	 */
	@Value("${org.hibernate.flushMode}")
	private String flushMode;

	/**
	 * connection timeout
	 */
	@Value("${db.connection.timeout}")
	private int connectionTimeout;

	/**
	 * connection idle timeout
	 */
	@Value("${db.connection.idle.timeout}")
	private int connectionIdleTimeout;

	/**
	 * connection m ax lifetime
	 */
	@Value("${db.connection.max.lifetime}")
	private int connectionMaxLifetime;

	/**
	 * validation timeout
	 */
	@Value("${db.connection.validation.timeout}")
	private int validationTimeout;

	/**
	 * minimum idle
	 */
	@Value("${db.connection.minimum.idle}")
	private int minimumIdle;

	/**
	 * connection max limit
	 */
	@Value("${db.connection.max.limit}")
	private int connectionMaxLimit;

	/**
	 * initialization fail timeout
	 */
	@Value("${db.initialization.fail.timeout}")
	private int initializationFailTimeout;

	/**
	 * com mysql jdbc driver
	 */
	@Value("${com.mysql.jdbc.Driver}")
	private String comMysqlJdbcDriver;

	/**
	 * bean jdbc template
	 * 
	 * @param dataSource data source
	 * @return jdbc template
	 */
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Bean entity manager
	 * 
	 * @return entity manager
	 * @throws GenericException generic exception
	 */
	@Bean(name = "entityManager")
	public EntityManager entityManager() throws GenericException {
		return entityManagerFactory().createEntityManager();
	}

	/**
	 * Bean data source
	 * 
	 * @return data source
	 * @throws GenericException generic exception
	 */
	@Bean(name = "dataSource")
	public DataSource dataSource() throws GenericException {
		HikariConfig config = new HikariConfig();
		try {
			config.setJdbcUrl(dbUrl);
			config.setUsername(dbUsername);
			config.setPassword(dbPassword);
			config.addDataSourceProperty("driverType", "thin");
			config.addDataSourceProperty("networkProtocol", networkProtocol);
			config.setConnectionTimeout(connectionTimeout);
			config.setIdleTimeout(connectionIdleTimeout);
			config.setInitializationFailTimeout(initializationFailTimeout);
			config.setMaxLifetime(connectionMaxLifetime);
			config.setValidationTimeout(validationTimeout);
			config.setMinimumIdle(minimumIdle);
			config.setMaximumPoolSize(connectionMaxLimit);
			return hikariDataSource(new HikariDataSource(config));
		} catch (Exception e) {
			LOGGER_DATABASECONFIG.error(
					"Ocurrió un error al insertar los parámetros de configuración de Hikari: " + e.getMessage(), e);
			throw new GenericException(
					"Ocurrió un error al insertar los parámetros de configuración de Hikari: " + e.getMessage());
		}
	}

	/**
	 * Hikari Data Source
	 * 
	 * @param ds Hikari Data Source
	 * @return Hikari Data Source
	 * @throws GenericException generic exception
	 */
	public HikariDataSource hikariDataSource(HikariDataSource ds) throws GenericException {
		try {
			ds.setDataSourceClassName(comMysqlJdbcDriver);
			ds.setLoginTimeout(connectionLoginTimeOut);
			return ds;
		} catch (Exception e) {
			LOGGER_DATABASECONFIG.error("Ocurrió un error al configurar el datasource de Hikari: " + e.getMessage(), e);
			ds.close();
			throw new GenericException("Ocurrió un error al configurar el datasource de Hikari: " + e.getMessage());
		}
	}

	/**
	 * Jpa Properties
	 * 
	 * @return properties
	 */
	Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.cache", hibernateCache);
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.max_fetch_depth", maxFetchDepth);
		properties.setProperty("hibernate.jdbc.fetch_size", fetchSize);
		properties.setProperty("hibernate.jdbc.batch_size", batchSize);
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("org.hibernate.flushMode", flushMode);
		properties.setProperty("hibernate.show_sql", showSql);
		properties.setProperty("hibernate.query.cache", hibernateQueryCache);
		return properties;
	}

	/**
	 * Bean entity manager factory
	 * 
	 * @return local container entity manager factory bean
	 * @throws GenericException generic exception
	 */
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() throws GenericException {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan("pe.entity.microservice.customer.example.model");
		emf.setPersistenceUnitName("default");
		emf.setJpaProperties(jpaProperties());
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	/**
	 * Bean transaction manager
	 * 
	 * @return jpa transaction manager
	 * @throws GenericException generic exception
	 */
	@Bean(name = "transactionManager")
	public JpaTransactionManager transactionManager() throws GenericException {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory());
		return tm;
	}
}
