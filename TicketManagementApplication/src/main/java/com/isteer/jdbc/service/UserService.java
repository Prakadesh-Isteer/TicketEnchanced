package com.isteer.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.isteer.jdbc.dto.AssignDto;
import com.isteer.jdbc.model.Role;
import com.isteer.jdbc.model.UserDetailsSerect;
import com.isteer.jdbc.repository.CustomerSecurityRepository;
import com.isteer.jdbc.repository.RoleBaseRepository;
import com.isteer.jdbc.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	@Autowired
	private CustomerSecurityRepository userRepo;
	 
	@Autowired
	private RoleBaseRepository roleRepo;

	@Autowired
	private JwtUtil jwtUtil;
	

	public int registerUser(UserDetailsSerect user) {
	    user.setPassword(new BCryptPasswordEncoder(7).encode(user.getPassword()));
	    List<Role> roles = roleRepo.getAllRoles();
	    user.setRoles(roles.stream().filter(r -> r.getRoleName().equals("USER")).toList());
	    return userRepo.registerUser(user);
	}
	

	public String loginUser(String userName) {
	  String token = null;
	  userRepo.findByUserName(userName);
	  token =  jwtUtil.generateToken(userName);
	  return token;
	}

	public int elevateUser (AssignDto role) {
		List<Role> wrkRoles = roleRepo.getAllRoles();
		int roleId = wrkRoles.stream().filter(r->r.getRoleName().equalsIgnoreCase(role.getRole())).mapToInt(Role ::getRoleId)
				.findFirst()
				.orElse(-1);
		if(roleId == -1) {
			return 0;
		}
		int status = userRepo.elevateRole(role.getUserName(), roleId);
			return status;	
	}
	
	public int removeUserRole(AssignDto role) {
		List<Role> wrkRole = roleRepo.getAllRoles();
		int roleId = wrkRole.stream().filter(r->r.getRoleName().equalsIgnoreCase(role.getRole())).mapToInt(Role ::getRoleId)
				.findFirst().orElse(-1);
		if(roleId == -1) {
			return 0;
		}
			int status = userRepo.deleteRole(role.getUserName(), roleId);
			return status;
	}
	
}
