/**
 * 
 */
package com.spring.boot.springsecuritydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.spring.boot.springsecuritydemo.security.UserPermissions.*;
import static com.spring.boot.springsecuritydemo.security.UserRole.*;

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
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/v1/welcome").permitAll()
			.antMatchers("/api/v1/students/**").hasRole(STUDENT.name())
			.antMatchers(HttpMethod.DELETE, "/management/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.POST, "/management/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT, "/management/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
			.antMatchers(HttpMethod.GET, "/management/api/v1/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails diane = User.builder()
				.username("Diane")
				.password(bCrypt.encode("password"))
				.authorities(STUDENT.getGrantedAuthorities())
				.build();
		
		UserDetails linda = User.builder()
			.username("Linda")
			.password(bCrypt.encode("password123"))
			.authorities(ADMIN.getGrantedAuthorities())
			.build();
		
		UserDetails tom = User.builder()
				.username("Tom")
				.password(bCrypt.encode("password123"))
				.authorities(ADMIN_TRAINEE.getGrantedAuthorities())
				.build();
		
		return new InMemoryUserDetailsManager(diane, linda, tom);
	}
}
