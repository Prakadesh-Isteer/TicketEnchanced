 package com.isteer.jdbc.util;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.isteer.jdbc.model.UserDetailsSerect;

public class UserPrincipal implements UserDetails {
	
	
	   private static final long serialVersionUID = 1L;

	    private UserDetailsSerect user;

	    public UserPrincipal(UserDetailsSerect user) {
	        this.user = user;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        // Check if roles are null or empty and handle gracefully
	        if (user.getRoles() == null || user.getRoles().isEmpty()) {
	            // Return an empty collection if no roles are available
	            return Collections.emptyList();
	        }

	        // Return roles if available
	        return user.getRoles().stream()
	                   .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
	                   .collect(Collectors.toList());
	    }

	    @Override
	    public String getPassword() {
	        return user.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return user.getUserName();
	    }

	
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	private UserDetailsSerect user;
//
//	public UserPrincipal(UserDetailsSerect user) {
//		this.user = user;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//	}
//
//	@Override
//	public String getPassword() {
//	return user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		
//		return user.getUserName();
//	}

}
