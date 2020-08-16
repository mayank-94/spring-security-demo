/**
 * 
 */
package com.spring.boot.springsecuritydemo.auth;

import java.util.Optional;

/**
 * @author Mayank
 *
 */
public interface ApplicationUserDAO {
	
	Optional<ApplicationUser> findUserByUsername(String username);

}
