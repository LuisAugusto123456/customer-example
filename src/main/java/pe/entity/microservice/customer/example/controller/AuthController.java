package pe.entity.microservice.customer.example.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.entity.microservice.customer.example.bo.AuthBO;
import pe.entity.microservice.customer.example.request.SignRequestObject;
import pe.entity.microservice.customer.example.security.jwt.JwtRefreshAuthenticationRequest;
import pe.entity.microservice.customer.example.util.ResponseConstants;

/**
 * Auth Controller
 * 
 * @author lpazd
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	/**
	 * controller log
	 */
	private static final Logger CONTROLLER_LOG = LogManager.getLogger(AuthController.class);

	/**
	 * auth bo
	 */
	@Autowired
	private AuthBO authBO;

	/**
	 * Sign In
	 * 
	 * @param signRequestObject request
	 * @return sign in
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";"
			+ ResponseConstants.CHARSET_UTF8, path = "/sign-in")
	public ResponseEntity<Object> signIn(@Valid @RequestBody SignRequestObject signRequestObject) {
		CONTROLLER_LOG.info("Ingreso metodo endpoint signIn");
		return authBO.signIn(signRequestObject.getUserName(), signRequestObject.getPassword());
	}

	/**
	 * JwtRefreshAuthenticationRequest
	 * 
	 * @param authenticationRequest request
	 * @return token
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";"
			+ ResponseConstants.CHARSET_UTF8, path = "/refresh")
	public ResponseEntity<Object> refreshToken(
			@Valid @RequestBody JwtRefreshAuthenticationRequest authenticationRequest) {
		CONTROLLER_LOG.info("Ingreso metodo endpoint refreshToken");
		return authBO.refreshAndGetAuthenticationToken(authenticationRequest);
	}
}
