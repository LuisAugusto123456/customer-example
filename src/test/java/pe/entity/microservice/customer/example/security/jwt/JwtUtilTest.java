package pe.entity.microservice.customer.example.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.entity.microservice.customer.example.enums.EClaims;
import pe.entity.microservice.customer.example.exception.InvalidTokenException;

/**
 * Jwt Util Test
 * 
 * @author lpazd
 *
 */
public class JwtUtilTest {

	private String secret = "3fc68991cb0ccc2f0cad25d4a4317b2cb58e881d";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		JwtUtil.setSecret(secret);
		JwtUtil.setExpirationAccessToken(Long.valueOf(120));
		JwtUtil.setExpirationRefreshToken(Long.valueOf(300));
	}

	@Test
	public void testGetClaims_whenTokenIsValid() throws InvalidTokenException {
		Assert.assertNotNull(JwtUtil.validateToken(createValidJWT(10, false, null)));
	}

	@Test(expected = InvalidTokenException.class)
	public void testGetClaims_whenTokenIsInvalid_expired() throws InvalidTokenException {
		JwtUtil.validateToken(createValidJWT(0, false, null));
	}

	@Test(expected = InvalidTokenException.class)
	public void testGetClaims_whenTokenIsInvalid_null() throws InvalidTokenException {
		JwtUtil.validateToken(null);
	}

	@Test(expected = InvalidTokenException.class)
	public void testGetClaims_whenTokenIsInvalid_refresh() throws InvalidTokenException {
		JwtUtil.validateToken(createValidJWT(10, true, "1234567890"));
	}

	@Test
	public void testGetClaims_whenTokenIsInvalid_emptyRefresh() throws InvalidTokenException {
		Assert.assertNotNull(JwtUtil.validateToken(createValidJWT(10, true, "")));
	}

	// Sample method to construct a JWT
	private String createValidJWT(int days, boolean isRefresh, String refreshToken) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.

		if (days > 0) {
			c.add(Calendar.DATE, days); // For future dates
		}

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId("1").setIssuedAt(new Date(System.currentTimeMillis())).setSubject("1")
				.setIssuer("1").signWith(signatureAlgorithm, new SecretKeySpec(
						DatatypeConverter.parseBase64Binary(secret), signatureAlgorithm.getJcaName()));

		// if it has been specified, let's add the expiration
		builder.setExpiration(c.getTime());
		if (isRefresh) {
			Claims claimsRq = Jwts.claims();
			claimsRq.setExpiration(c.getTime());
			claimsRq.put("gen", refreshToken);
			builder.setClaims(claimsRq);
		}
		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	@Test
	public void testPrepareToken_whenTokenIsValid() throws InvalidTokenException {
		List<Claim> claims = new ArrayList<>();
		claims.add(new Claim(EClaims.NAME.getCode(), "userName"));
		Assert.assertNotNull(JwtUtil.prepareToken(new JwtAuthenticationRequest(claims, "id")));
	}

	@Test
	public void testRefreshToken_whenTokenIsValid() throws InvalidTokenException {
		List<Claim> claims = new ArrayList<>();
		claims.add(new Claim(EClaims.NAME.getCode(), "userName"));
		Assert.assertNotNull(JwtUtil.prepareRefreshToken(new JwtAuthenticationRequest(claims, "id"), 123456));
	}

	@Test
	public void testReturnToken_whenTokenIsValid() throws IOException {
		Assert.assertNotNull(JwtUtil.returnToken(createValidJWT(10, false, null)));
	}

	@Test
	public void testReturnRefreshToken_whenTokenIsValid() throws IOException {
		Assert.assertNotNull(JwtUtil.returnRefreshToken(createValidJWT(10, true, "1"), 1));
	}

	@Test
	public void testGetClaimsFromToken_whenTokenIsValid() throws IOException {
		Assert.assertNotNull(JwtUtil.getClaimsFromToken(createValidJWT(10, false, null), secret));
	}
}
