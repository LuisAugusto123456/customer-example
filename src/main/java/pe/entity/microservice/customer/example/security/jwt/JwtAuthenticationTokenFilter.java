
package pe.entity.microservice.customer.example.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import pe.entity.microservice.customer.example.enums.ERequestContext;

/**
 * Jwt Authentication Token Filter
 * 
 * @author lpazd
 *
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	/**
	 * logger jwt
	 */
	private static final Logger LOGGER_JWT = LogManager.getLogger(JwtAuthenticationTokenFilter.class);

	/**
	 * User Details Service
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * token Header
	 */
	@Value("X-Authorization")
	private String tokenHeader;

	/**
	 * secret
	 */
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * do Filter Internal
	 */
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		LOGGER_JWT.info("getRequestURI: " + request.getRequestURI());
		LOGGER_JWT.info("getRequestURL: " + request.getRequestURL());
		String authToken = request.getHeader(this.tokenHeader);
		LOGGER_JWT.debug("authToken = " + authToken);
		String username = "Usuario Autorizado";
		if (authToken == null) {
			LOGGER_JWT.info("TokenHeader nulo");
		} else {
			LOGGER_JWT.info("Token recibido");
		}
		Claims claims = null;
		try {

			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				LOGGER_JWT.info("Accediendo a SecurityContextHolder ");
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				if (authToken != null) {
					JwtUtil.setSecret(secret);
					claims = JwtUtil.validateToken(authToken);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					LOGGER_JWT.info("Usuario autenticado, configurado contexto de seguridad");
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					LOGGER_JWT.info("Usuario no autenticado");
				}
			}
		} catch (Exception e) {
			LOGGER_JWT.error("No se pudo obtener el token: " + e.getMessage(), e);
		}
		request.setAttribute(ERequestContext.CLAIMS.getCode(), claims);
		LOGGER_JWT.info("doFilter requets: " + request);
		LOGGER_JWT.info("doFilter response: " + response);

		chain.doFilter(request, response);
	}
}
