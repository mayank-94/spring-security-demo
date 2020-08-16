/**
 * 
 */
package com.spring.boot.springsecuritydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.boot.springsecuritydemo.auth.ApplicationUserService;

//import static com.spring.boot.springsecuritydemo.security.UserPermissions.*;
import static com.spring.boot.springsecuritydemo.security.UserRole.*;

/**
 * @author Mayank
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private ApplicationUserService applicationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/v1/welcome").permitAll()
			.antMatchers("/api/v1/students/**").hasRole(STUDENT.name())
//			.antMatchers(HttpMethod.DELETE, "/management/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.POST, "/management/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.PUT, "/management/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.GET, "/management/api/v1/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
			.anyRequest().authenticated()
			.and()
			.httpBasic();
			//.formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoProider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoProider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCrypt);
		provider.setUserDetailsService(applicationService);
		return provider;
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		
//		UserDetails diane = User.builder()
//				.username("Diane")
//				.password(bCrypt.encode("password"))
//				.authorities(STUDENT.getGrantedAuthorities())
//				.build();
//		
//		UserDetails linda = User.builder()
//			.username("Linda")
//			.password(bCrypt.encode("password123"))
//			.authorities(ADMIN.getGrantedAuthorities())
//			.build();
//		
//		UserDetails tom = User.builder()
//				.username("Tom")
//				.password(bCrypt.encode("password123"))
//				.authorities(ADMIN_TRAINEE.getGrantedAuthorities())
//				.build();
//		
//		return new InMemoryUserDetailsManager(diane, linda, tom);
//	}
}
