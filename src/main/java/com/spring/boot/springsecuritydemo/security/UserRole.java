/**
 * 
 */
package com.spring.boot.springsecuritydemo.security;

import java.util.Set;

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
	
}
