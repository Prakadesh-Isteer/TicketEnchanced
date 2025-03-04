package com.isteer.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isteer.jdbc.repository.RoleBaseRepository;
@Service
public class RoleBaseAccessService {
	
	@Autowired
	RoleBaseRepository repo;
	
	public int addRole(String addRole) {
		return repo.addRole(addRole);
	}
	public int removeRole(String removeRole) {
		return repo.removeRole(removeRole);
	}
	
}
