package com.isteer.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isteer.jdbc.dto.UrlDto;
import com.isteer.jdbc.model.HttpMethodRoleRights;
import com.isteer.jdbc.model.RequestPermission;
import com.isteer.jdbc.model.Role;
import com.isteer.jdbc.model.UserDetailsSerect;
import com.isteer.jdbc.repository.CustomerSecurityRepository;
import com.isteer.jdbc.repository.RequestPermissionRepo;
import com.isteer.jdbc.repository.RoleBaseRepository;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestPermissionService {
	
	
	@Autowired
	RoleBaseRepository Rolerepo;
	@Autowired
	RequestPermissionRepo requestRepo;
	@Autowired
	CustomerSecurityRepository userRepo;
	@Autowired
	HttpServletRequest request;

	public boolean hasPermission() {
		String userName = request.getUserPrincipal().getName();
		String url = request.getRequestURI();
		String httpMethod = request.getMethod();
		UserDetailsSerect user = userRepo.findByUserName(userName);
		System.out.println(user.getRoles());
		List<RequestPermission> permission = requestRepo.findUrl(url);
		List<HttpMethodRoleRights> methodPermission = requestRepo.findHttpMethod(httpMethod);
		System.out.println(methodPermission);
		System.out.println(httpMethod);
		boolean urlPermission = user.getRoles()
				.stream().map(Role:: getRoleId)
				.anyMatch(roleId -> permission.stream()
						.map(RequestPermission ::getRoleId)
						.anyMatch(roleId :: equals));
		boolean  methodPermissionStatus = user.getRoles()
				.stream().map(Role ::getRoleId ).anyMatch(roleId ->methodPermission.stream().map(HttpMethodRoleRights :: getRoleId).anyMatch(roleId :: equals));
	     
		System.out.println(urlPermission);
		System.out.println(methodPermissionStatus);
	if(urlPermission && methodPermissionStatus) 
		return true;
	
	return false;
	}
	
	public int addUrl(UrlDto url){
		int roleId = Rolerepo.getRoleId(url.getRole());
		int status = requestRepo.addUrl(url.getUrl(), roleId);
		return status;
		
	}
	public int removeUrl(UrlDto url) {
		int roleId = Rolerepo.getRoleId(url.getRole());
		int status = requestRepo.removeUrl(url.getUrl(), roleId);
		return status;
	}
	
	public int addHttp(HttpMethodRoleRights role) {
		int roleId = Rolerepo.getRoleIdInt(role.getRoleId());
		int status = requestRepo.addHttpMethod(role.getHttpMethod(), roleId);
		return status;
		
	}
	
	public int removeHttpMethod(HttpMethodRoleRights role) {
		int roleId = Rolerepo.getRoleIdInt(role.getRoleId());
		int status = requestRepo.removeHttpMethod(role.getHttpMethod(), roleId);
		return status;
	}
	

}
