package com.isteer.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isteer.jdbc.model.UserDetailsSerect;
import com.isteer.jdbc.repository.CustomerSecurityRepository;
import com.isteer.jdbc.util.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
      CustomerSecurityRepository repo;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDetailsSerect user = repo.findByUserName(userName);
		if(user !=null) {
			return new UserPrincipal(user);
		}
		throw new UsernameNotFoundException("user not found") ;
	}

}
