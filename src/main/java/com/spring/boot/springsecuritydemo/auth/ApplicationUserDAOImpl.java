/**
 * 
 */
package com.spring.boot.springsecuritydemo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import static com.spring.boot.springsecuritydemo.security.UserRole.*;

/**
 * @author Mayank
 *
 */
@Repository
@Qualifier("staticImpl")
public class ApplicationUserDAOImpl implements ApplicationUserDAO{
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Override
	public Optional<ApplicationUser> findUserByUsername(String username) {
		return getApplicationUsers().stream()
				.filter(appUser -> appUser.getUsername().equals(username))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers(){
		
		List<ApplicationUser> users = Lists.newArrayList(
			new ApplicationUser(
				new User("Diane", bCrypt.encode("password"), true, true, true, true, STUDENT.getGrantedAuthorities())),
			new ApplicationUser(
				new User("Linda", bCrypt.encode("password123"), true, true, true, true, ADMIN.getGrantedAuthorities())),
			new ApplicationUser(
				new User("Tom", bCrypt.encode("password"), true, true, true, true, ADMIN_TRAINEE.getGrantedAuthorities()))
		);
		
		return users;
	}
	
}
