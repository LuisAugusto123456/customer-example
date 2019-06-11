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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;
import pe.entity.microservice.customer.example.bo.AuthBO;
import pe.entity.microservice.customer.example.request.SignRequestObject;
import pe.entity.microservice.customer.example.response.UserResponseObject;
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
@Api("Set of endpoints for sign in and token of customer.")
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
	@ApiOperation("Returns data user.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully sign in", response = UserResponseObject.class, examples = @io.swagger.annotations.Example(value = {
					@ExampleProperty(mediaType = "Example json", value = "{\n" + "	\"user_uuid\": \"aaaaaaaaa\",\n"
							+ "	\"access_token\": \"xxxxxxxx\",\n" + "	\"refresh_token\": \"zzzzzzzz\"\n" + "}") })),
			@ApiResponse(code = 204, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
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
	@ApiOperation("Returns access token and refresh token.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully access token and refresh token", response = UserResponseObject.class, examples = @io.swagger.annotations.Example(value = {
					@ExampleProperty(mediaType = "Example json", value = "{\n" + "	\"user_uuid\": \"aaaaaaaaa\",\n"
							+ "	\"access_token\": \"xxxxxxxx\",\n" + "	\"refresh_token\": \"zzzzzzzz\"\n" + "}") })),
			@ApiResponse(code = 403, message = "Forbidden for not refresh token"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> refreshToken(
			@Valid @RequestBody JwtRefreshAuthenticationRequest authenticationRequest) {
		CONTROLLER_LOG.info("Ingreso metodo endpoint refreshToken");
		return authBO.refreshAndGetAuthenticationToken(authenticationRequest);
	}
}
