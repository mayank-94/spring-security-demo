/**
 * 
 */
package com.spring.boot.springsecuritydemo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Mayank
 *
 */
@Service
public class ApplicationUserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("staticImpl")
	private ApplicationUserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
	}

}
