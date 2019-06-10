package pe.entity.microservice.customer.example.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import pe.entity.microservice.customer.example.enums.EClaims;
import pe.entity.microservice.customer.example.enums.EResponse;
import pe.entity.microservice.customer.example.model.User;
import pe.entity.microservice.customer.example.response.GenericResponseObject;
import pe.entity.microservice.customer.example.response.UserResponseObject;
import pe.entity.microservice.customer.example.security.jwt.Claim;
import pe.entity.microservice.customer.example.security.jwt.JwtAuthenticationRequest;
import pe.entity.microservice.customer.example.security.jwt.JwtRefreshAuthenticationRequest;
import pe.entity.microservice.customer.example.security.jwt.JwtUtil;
import pe.entity.microservice.customer.example.service.UserService;

/**
 * Auth BO
 * 
 * @author lpazd
 *
 */
@Component
public class AuthBO {

	/**
	 * logger bo
	 */
	private static final Logger LOGGER_BO = LogManager.getLogger(AuthBO.class);

	/**
	 * secret
	 */
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * user service
	 */
	@Autowired
	private UserService userService;

	/**
	 * sign In
	 * 
	 * @param userName user name
	 * @param password password
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 10)
	public ResponseEntity<Object> signIn(String userName, String password) {
		try {
			LOGGER_BO.info("validamos credenciales");
			User user = userService.findByUserNameAndPassword(userName, password);
			if (ObjectUtils.isEmpty(user)) {
				LOGGER_BO.info("No se obtuvo resultado en la busqueda - signIn");
				return ResponseEntity.noContent().build();
			}
			LOGGER_BO.info("Obtenemos tokens");
			JwtAuthenticationRequest authenticationRequest = getJwtAuthenticationRequest(user.getUuid(),
					user.getUserName());
			JwtUtil.setSecret(secret);
			return ResponseEntity.ok(new UserResponseObject(user.getUuid(), JwtUtil.prepareToken(authenticationRequest),
					JwtUtil.prepareRefreshToken(authenticationRequest, user.getId().intValue())));
		} catch (Exception e) {
			LOGGER_BO.error("Error interno del servidor - signIn: " + e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponseObject(
					EResponse.NON_SPECIFIC.getCode(), EResponse.NON_SPECIFIC.getKeyMessage()));
		}
	}

	/**
	 * Refresh And Get Authentication Token
	 * 
	 * @param authenticationRequest authentication Request
	 * @return token
	 */
	public ResponseEntity<Object> refreshAndGetAuthenticationToken(
			JwtRefreshAuthenticationRequest authenticationRequest) {
		try {
			JwtUtil.setSecret(secret);
			LOGGER_BO.info("generamos access token");
			String accessToken = JwtUtil.returnToken(authenticationRequest.getToken());
			Map<String, Object> claimsRequest = JwtUtil.getClaimsFromToken(accessToken, secret);
			LOGGER_BO.info("Validamos si el token es refresh y no access");
			if (ObjectUtils.isEmpty(claimsRequest.get(EClaims.GEN.getCode()))) {
				LOGGER_BO.warn("Token de acceso. No es posible refrescar.");
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseObject(
						EResponse.NON_SPECIFIC.getCode(), EResponse.NON_SPECIFIC.getKeyMessage()));
			}
			LOGGER_BO.info("generamos refresh token");
			return ResponseEntity.ok(new UserResponseObject(claimsRequest.get(EClaims.ID.getCode()).toString(),
					accessToken, JwtUtil.returnRefreshToken(accessToken,
							Integer.parseInt(claimsRequest.get(EClaims.ID.getCode()).toString()))));
		} catch (Exception e) {
			LOGGER_BO.error("Error interno del servidor - refreshAndGetAuthenticationToken: " + e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponseObject(
					EResponse.NON_SPECIFIC.getCode(), EResponse.NON_SPECIFIC.getKeyMessage()));
		}
	}

	/**
	 * Get Jwt Authentication Request
	 * 
	 * @param uuid     uuid
	 * @param userName user name
	 * @return Jwt Authentication Request
	 */
	public JwtAuthenticationRequest getJwtAuthenticationRequest(String uuid, String userName) {
		List<Claim> claims = new ArrayList<>();
		claims.add(new Claim(EClaims.NAME.getCode(), userName));
		return (new JwtAuthenticationRequest(claims, uuid));
	}
}
