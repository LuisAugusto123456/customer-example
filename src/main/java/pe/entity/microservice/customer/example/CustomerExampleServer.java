package pe.entity.microservice.customer.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Class server
 * 
 * @author lpazd
 *
 */
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = { "pe.entity.microservice.customer.example",
		"pe.entity.microservice.customer.example.model" })
@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class,
		DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class CustomerExampleServer {

	/**
	 * server logger
	 */
	private static final Logger SERVER_LOGGER = LogManager.getLogger(CustomerExampleServer.class);

	/**
	 * env target
	 */
	private static final String ENV_TARGET = "envTarget";

	/**
	 * Config dev
	 * 
	 * @author lpazd
	 *
	 */
	@Configuration
	@Profile("dev")
	@PropertySource({ "${propertiesDir}/${envTarget:dev}/config/config-${envTarget:dev}.properties" })
	@PropertySource({ "${propertiesDir}/${envTarget:dev}/secrets/config-${envTarget:dev}.properties" })
	static class ConfigDev {
		@Bean
		InitializingBean init() {
			return () -> SERVER_LOGGER.info("Enviroment: Dev");
		}
	}

	/**
	 * Main
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(CustomerExampleServer.class);
		application.setAdditionalProfiles(
				System.getProperty(ENV_TARGET) == null ? System.getenv(ENV_TARGET) : System.getProperty(ENV_TARGET));
		application.run(args);
	}
}
