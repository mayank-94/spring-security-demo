/**
 * 
 */
package com.spring.boot.springsecuritydemo.auth;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Mayank
 *
 */
public class User {
	
	private String username;
	private String password;
	private boolean isAcountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnables;
	private Set<? extends GrantedAuthority> authorities;
	
	public User() {
		super();
	}
	
	public User(String username, String password, boolean isAcountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnables, Set<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.isAcountNonExpired = isAcountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnables = isEnables;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAcountNonExpired() {
		return isAcountNonExpired;
	}
	public void setAcountNonExpired(boolean isAcountNonExpired) {
		this.isAcountNonExpired = isAcountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	public boolean isEnables() {
		return isEnables;
	}
	public void setEnables(boolean isEnables) {
		this.isEnables = isEnables;
	}
	public Set<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
}
