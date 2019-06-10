package pe.entity.microservice.customer.example.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Class Jwt User Factory
 * 
 * @author lpazd
 *
 */
public final class JwtUserFactory {

	/**
	 * Jwt User Factory
	 */
	private JwtUserFactory() {
	}

	/**
	 * Jwt User Create
	 * 
	 * @return
	 */
	public static JwtUser create() {
		ArrayList<String> authList = new ArrayList<>();
		authList.add("role");
		return new JwtUser(1L, "Usuario Autorizado", mapToGrantedAuthorities(authList));
	}

	/**
	 * Map To Granted Authorities
	 * 
	 * @param authorities authorities
	 * @return List To Granted Authorities
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());
	}
}
