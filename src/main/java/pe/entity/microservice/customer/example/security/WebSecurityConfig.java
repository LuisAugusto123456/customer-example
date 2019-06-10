package pe.entity.microservice.customer.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.entity.microservice.customer.example.security.jwt.JwtAuthenticationEntryPoint;
import pe.entity.microservice.customer.example.security.jwt.JwtAuthenticationTokenFilter;

/**
 * Class Web Security Config
 * 
 * @author lpazd
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Unauthorized Handler
	 */
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	/**
	 * User Details Service
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Configure Authentication
	 * 
	 * @param authenticationManagerBuilder Authentication Manager Builder
	 * @throws Exception exception
	 */
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Password Encoder
	 * 
	 * @return Password Encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Jwt Authentication Token Filter
	 * 
	 * @return Jwt Authentication Token Filter
	 */
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter();
	}

	/**
	 * configure
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity

				.csrf().disable()

				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)

				.headers().disable()

				.requestCache().disable()

				.authorizeRequests()

				.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js")
				
				.permitAll().antMatchers("/auth/**")

				.permitAll().anyRequest().authenticated();
	}
}
