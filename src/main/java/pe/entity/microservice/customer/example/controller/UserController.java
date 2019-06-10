package pe.entity.microservice.customer.example.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.entity.microservice.customer.example.bo.UserBO;
import pe.entity.microservice.customer.example.request.CustomerRequestObject;
import pe.entity.microservice.customer.example.util.ResponseConstants;

/**
 * User Controller
 * 
 * @author lpazd
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

	/**
	 * controller log
	 */
	private static final Logger CONTROLLER_LOG = LogManager.getLogger(UserController.class);

	/**
	 * user bo
	 */
	@Autowired
	private UserBO userBO;

	/**
	 * Save user
	 * 
	 * @param customerRequestObject request
	 * @return user
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";"
			+ ResponseConstants.CHARSET_UTF8)
	public ResponseEntity<Object> saveUser(@Valid @RequestBody CustomerRequestObject customerRequestObject) {
		CONTROLLER_LOG.info("Ingreso metodo endpoint saveUser");
		return userBO.saveUser(customerRequestObject.getUserName(), customerRequestObject.getPassword(),
				customerRequestObject.getFirstName(), customerRequestObject.getLastName(),
				customerRequestObject.getAge(), customerRequestObject.getBirthDate());
	}

	/**
	 * kpi
	 * 
	 * @return kpi
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";" + ResponseConstants.CHARSET_UTF8, path = "/kpi")
	public ResponseEntity<Object> kpi() {
		CONTROLLER_LOG.info("Ingreso metodo endpoint kpi");
		return userBO.kpi();
	}

	/**
	 * customer list
	 * 
	 * @return customer list
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";" + ResponseConstants.CHARSET_UTF8)
	public ResponseEntity<Object> customerList() {
		CONTROLLER_LOG.info("Ingreso metodo endpoint customerList");
		return userBO.customerList();
	}
}
