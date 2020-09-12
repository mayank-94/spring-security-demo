/**
 * 
 */
package com.spring.boot.springsecuritydemo.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import static com.spring.boot.springsecuritydemo.security.UserPermissions.*;

/**
 * @author Mayank
 *
 */
public enum UserRole {
	
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
	ADMIN_TRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));
	
	private final Set<UserPermissions> permissions;
	
	UserRole(Set<UserPermissions> permissions){
		this.permissions = permissions;
	}

	public Set<UserPermissions> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return permissions;
	}
	
}
