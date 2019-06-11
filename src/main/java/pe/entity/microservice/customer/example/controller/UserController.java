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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;
import pe.entity.microservice.customer.example.bo.UserBO;
import pe.entity.microservice.customer.example.request.CustomerRequestObject;
import pe.entity.microservice.customer.example.response.CustomerResponseObject;
import pe.entity.microservice.customer.example.response.KpiResponseObject;
import pe.entity.microservice.customer.example.response.UserResponseObject;
import pe.entity.microservice.customer.example.util.ResponseConstants;

/**
 * User Controller
 * 
 * @author lpazd
 *
 */
@Controller
@RequestMapping("/users")
@Api("Set of endpoints for save user, Kpi and list of customers.")
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
	@ApiOperation("Returns registered user.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully save user", response = UserResponseObject.class, examples = @io.swagger.annotations.Example(value = {
					@ExampleProperty(mediaType = "Example json", value = "{\n" + "	\"user_uuid\": \"aaaaaaaaa\",\n"
							+ "	\"access_token\": \"xxxxxxxx\",\n" + "	\"refresh_token\": \"zzzzzzzz\"\n" + "}") })),
			@ApiResponse(code = 400, message = "User name exist"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
	@ApiOperation("Returns kpi (average and standard deviation).")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";" + ResponseConstants.CHARSET_UTF8, path = "/kpi")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully kpi (average and standard deviation)", response = KpiResponseObject.class, examples = @io.swagger.annotations.Example(value = {
					@ExampleProperty(mediaType = "Example json", value = "{\n" + "	\"average\": 118.0,\n"
							+ "	\"standardDeviation\": 23.6\n" + "}") })),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> kpi() {
		CONTROLLER_LOG.info("Ingreso metodo endpoint kpi");
		return userBO.kpi();
	}

	/**
	 * customer list
	 * 
	 * @return customer list
	 */
	@ApiOperation("Returns list of all customers.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";" + ResponseConstants.CHARSET_UTF8)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully customers list", response = CustomerResponseObject.class, examples = @io.swagger.annotations.Example(value = {
					@ExampleProperty(mediaType = "Example json", value = "[{\n" + "		\"uuid\": \"xxxxxxx\",\n"
							+ "		\"firstName\": \"Pepe\",\n" + "		\"lastName\": \"Grillo\",\n"
							+ "		\"age\": 32,\n" + "		\"birthDate\": \"13/04/1987\",\n"
							+ "		\"deathDate\": \"13/04/2050\"\n" + "	},\n" + "	{\n"
							+ "		\"uuid\": \"xxxxxxx\",\n" + "		\"firstName\": \"Pepe\",\n"
							+ "		\"lastName\": \"Grillo\",\n" + "		\"age\": 32,\n"
							+ "		\"birthDate\": \"13/04/1987\",\n" + "		\"deathDate\": \"13/04/2050\"\n"
							+ "	}\n" + "]") })),
			@ApiResponse(code = 204, message = "Users not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> customerList() {
		CONTROLLER_LOG.info("Ingreso metodo endpoint customerList");
		return userBO.customerList();
	}
}
