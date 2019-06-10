package pe.entity.microservice.customer.example.security.jwt;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Jwt User
 * 
 * @author lpazd
 *
 */
public class JwtUser implements UserDetails {

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private final Long id;

	/**
	 * user name
	 */
	private final String username;

	/**
	 * authorities
	 */
	private final Collection<? extends GrantedAuthority> authorities;

	/**
	 * Jwt User
	 * 
	 * @param id          id
	 * @param username    user name
	 * @param authorities authorities
	 */
	public JwtUser(Long id, String username, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.authorities = authorities;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */
	@JsonIgnore
	public Long getId() {
		return id;
	}

	/**
	 * Get User Name
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * Is Account Non Expired
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Is Account Non Locked
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Is Credentials Non Expired
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Get Authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Is Enabled
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Get Password
	 */
	@Override
	public String getPassword() {
		return "password";
	}

}
