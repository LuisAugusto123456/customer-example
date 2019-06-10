package pe.entity.microservice.customer.example.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import pe.entity.microservice.customer.example.security.jwt.JwtUserFactory;

/**
 * Jwt User Details Service Impl
 * 
 * @author lpazd
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	/**
	 * Load User By Username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		return JwtUserFactory.create();
	}
}
