package pe.entity.microservice.customer.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.entity.microservice.customer.example.bo.ExampleBO;
import pe.entity.microservice.customer.example.util.ResponseConstants;

/**
 * Example Controller
 * 
 * @author lpazd
 *
 */
@Controller
@RequestMapping("/examples")
public class ExampleController {

	/**
	 * example bo
	 */
	@Autowired
	private ExampleBO exampleBO;

	/**
	 * example flux
	 * 
	 * @return success response
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";" + ResponseConstants.CHARSET_UTF8, path = "/flux")
	public ResponseEntity<Object> exampleFlux() {
		return exampleBO.exampleFlux();
	}
}
