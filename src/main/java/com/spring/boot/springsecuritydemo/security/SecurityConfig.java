/**
 * 
 */
package com.spring.boot.springsecuritydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Mayank
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/v1/welcome").permitAll()
			.antMatchers("/api/v1/students/**").hasRole(UserRole.STUDENT.name())
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails diane = User.builder()
				.username("Diane")
				.password(bCrypt.encode("password"))
				.roles(UserRole.STUDENT.name())
				.build();
		
		UserDetails linda = User.builder()
			.username("Linda")
			.password(bCrypt.encode("password123"))
			.roles(UserRole.ADMIN.name())
			.build();
		
		UserDetails tom = User.builder()
				.username("Tom")
				.password(bCrypt.encode("password123"))
				.roles(UserRole.ADMIN_TRAINEE.name())
				.build();
		
		return new InMemoryUserDetailsManager(diane, linda, tom);
	}
}
