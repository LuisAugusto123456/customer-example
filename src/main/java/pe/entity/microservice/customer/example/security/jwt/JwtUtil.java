package pe.entity.microservice.customer.example.security.jwt;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.entity.microservice.customer.example.enums.EClaims;
import pe.entity.microservice.customer.example.exception.InvalidTokenException;

/**
 * Jwt Util
 * 
 * @author lpazd
 *
 */
public class JwtUtil {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(JwtUtil.class);

	/**
	 * secret
	 */
	private static String secret;

	/**
	 * expiration access token
	 */
	private static Long expirationAccessToken;

	/**
	 * expiration refresh token
	 */
	private static Long expirationRefreshToken;

	/**
	 * Set Secret
	 * 
	 * @param secret secret
	 */
	public static void setSecret(String secret) {
		JwtUtil.secret = secret;
	}

	/**
	 * Set Expiration Access Token
	 * 
	 * @param expirationAccessToken expiration Access Token
	 */
	public static void setExpirationAccessToken(Long expirationAccessToken) {
		JwtUtil.expirationAccessToken = expirationAccessToken;
	}

	/**
	 * Set Expiration Refresh Token
	 * 
	 * @param expirationRefreshToken expiration Refresh Token
	 */
	public static void setExpirationRefreshToken(Long expirationRefreshToken) {
		JwtUtil.expirationRefreshToken = expirationRefreshToken;
	}

	/**
	 * Jwt Util
	 */
	private JwtUtil() {
	}

	/**
	 * Get Claims From Token
	 * 
	 * @param token token
	 * @return token
	 */
	private static Claims getClaimsFromToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			LOGGER.warn("Error al validar el token:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Validate Token
	 * 
	 * @param authToken token
	 * @return claims
	 * @throws InvalidTokenException Invalid Token Exception
	 */
	public static Claims validateToken(String authToken) throws InvalidTokenException {
		Claims claims = getClaimsFromToken(authToken);
		if (authToken != null && (!isTokenExpired(claims)) && !isRefresh(claims.get(EClaims.GEN.getCode()))) {
			return claims;
		}
		throw new InvalidTokenException();
	}

	/**
	 * Get Expiration Date From Token
	 * 
	 * @param claims claims
	 * @return date
	 */
	private static Date getExpirationDateFromToken(Claims claims) {
		if (claims != null) {
			return claims.getExpiration();
		}
		return null;
	}

	/**
	 * Is Token Expired
	 * 
	 * @param claims
	 * @return
	 */
	private static boolean isTokenExpired(Claims claims) {
		final Date expiration = getExpirationDateFromToken(claims);
		if (expiration == null) {
			return true;
		}
		LOGGER.info("Fecha expiracion:" + expiration);
		return expiration.before(new Date());
	}

	/**
	 * Is Refresh
	 * 
	 * @param claim claim
	 * @return validate
	 */
	private static boolean isRefresh(Object claim) {
		return !(claim == null || StringUtils.isEmpty(claim.toString()));
	}

	/**
	 * Prepare Token
	 * 
	 * @param authenticationRequest authentication Request
	 * @return access token
	 */
	public static String prepareToken(JwtAuthenticationRequest authenticationRequest) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(EClaims.CREATED.getCode(), new Date());
		claims.put(EClaims.ID.getCode(),
				authenticationRequest.getId().equals("") ? null : authenticationRequest.getId());
		claims.put(EClaims.SUB.getCode(), authenticationRequest.getId());

		if (authenticationRequest.getClaims() != null) {
			for (Claim claim : authenticationRequest.getClaims()) {
				claims.put(claim.getKey(), claim.getValue());
			}
		}
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expirationAccessToken))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * 
	 * @param authenticationRequest
	 * @param gen
	 * @return
	 */
	public static String prepareRefreshToken(JwtAuthenticationRequest authenticationRequest, int gen) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(EClaims.CREATED.getCode(), new Date());
		claims.put(EClaims.ID.getCode(),
				authenticationRequest.getId().equals("") ? null : authenticationRequest.getId());
		claims.put(EClaims.SUB.getCode(), authenticationRequest.getId());
		if (authenticationRequest.getClaims() != null) {
			for (Claim claim : authenticationRequest.getClaims()) {
				claims.put(claim.getKey(), claim.getValue());
			}
		}
		claims.put(EClaims.GEN.getCode(), gen);
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expirationRefreshToken))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * Generate Expiration Date
	 * 
	 * @param expiration expiration
	 * @return expiration date
	 */
	private static Date generateExpirationDate(Long expiration) {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * return Token
	 * 
	 * @param token token
	 * @return token
	 * @throws IOException
	 */
	public static String returnToken(String token) throws IOException {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				claims.remove(EClaims.GEN.getCode());
			}
			return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expirationAccessToken))
					.signWith(SignatureAlgorithm.HS512, secret).compact();

		} catch (ExpiredJwtException e) {
			Base64.Decoder decoder = Base64.getUrlDecoder();
			String src = token;
			String[] parts = src.split("\\.");
			ObjectMapper mapper = new ObjectMapper();
			String json = new String(decoder.decode(parts[1]));

			Map<String, Object> claims = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
			claims.put(EClaims.CREATED.getCode(), new Date());
			return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expirationAccessToken))
					.signWith(SignatureAlgorithm.HS512, secret).compact();
		} catch (Exception e) {
			LOGGER.error("Error de Token:" + e.getMessage());
		}
		return null;

	}

	/**
	 * Utilitario para obtener claims del token
	 * 
	 * @param token Token de autorizacion
	 * @return Claims Listado de claims del token
	 */
	public static Claims getClaimsFromToken(String token, String secret) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			throw new ExpiredJwtException(null, null, "Token expirado");
		}
	}

	/**
	 * return Refresh Token
	 * 
	 * @param token token
	 * @param gen   gen
	 * @return token
	 */
	public static String returnRefreshToken(String token, int gen) {
		Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		final Claims claims = getClaimsFromToken(token);
		if (claims != null) {
			claims.remove(EClaims.CREATED.getCode());
			claims.put(EClaims.CREATED.getCode(), new Date());
			claims.put(EClaims.GEN.getCode(), gen);
		}
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expirationRefreshToken))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
