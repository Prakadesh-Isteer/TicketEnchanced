package com.isteer.jdbc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import com.isteer.jdbc.dto.AssignDto;
import com.isteer.jdbc.dto.StatusErrorDto;
import com.isteer.jdbc.dto.SuccessMessageDto;
import com.isteer.jdbc.dto.TokenDto;
import com.isteer.jdbc.dto.UrlDto;
import com.isteer.jdbc.enums.TicketEnums;
import com.isteer.jdbc.model.HttpMethodRoleRights;
import com.isteer.jdbc.model.UserDetailsSerect;
import com.isteer.jdbc.service.RequestPermissionService;
import com.isteer.jdbc.service.RoleBaseAccessService;
import com.isteer.jdbc.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/appSteer")
public class UsersTicketManagementController {
	
	@Autowired
	UserService service;
	@Autowired
	RoleBaseAccessService roleService;
	@Autowired
	RequestPermissionService permissionService;
	public static  Logger logging = LogManager.getLogger(UsersTicketManagementController.class);
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@Validated @RequestBody UserDetailsSerect user) {
		int status = service.registerUser(user);
		if(status>0) {
			SuccessMessageDto message = new SuccessMessageDto(TicketEnums.REGISTERATION_SUCESS.getStatusCode(), TicketEnums.REGISTERATION_SUCESS.getStatusMessage());
			return ResponseEntity.ok(message);
		}
		StatusErrorDto error = new StatusErrorDto(TicketEnums.REGISTERATION_FAILED.getStatusCode(),TicketEnums.REGISTERATION_FAILED.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String token = service.loginUser(userName);
		if(token != null) {
			TokenDto message = new TokenDto(1234,userName,token);
			return ResponseEntity.ok(message);
		}
		TokenDto message = new TokenDto(9320, userName, "User LOGIN Failed");
		return ResponseEntity.badRequest().body(message);
		
	}
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PatchMapping("/elevateUser")
	public ResponseEntity<?> elevateUser(@Valid @RequestBody AssignDto userName) {
		int status = service.elevateUser(userName);
		if(status>0) {
			SuccessMessageDto message = new SuccessMessageDto(TicketEnums.ROLE_ELEVATION_SUCCESS.getStatusCode(), TicketEnums.ROLE_ELEVATION_SUCCESS.getStatusMessage());
			return ResponseEntity.ok(message);
		}
		
		StatusErrorDto error = new StatusErrorDto(TicketEnums.ROLE_ELEVATION_FAILED.getStatusCode(),TicketEnums.ROLE_ELEVATION_FAILED.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PatchMapping("/deElevate")
	public ResponseEntity<?> deElevateRole(@Valid@RequestBody AssignDto userName) {
       int status = service.removeUserRole(userName);
       if(status>0) {
    	   SuccessMessageDto message = new SuccessMessageDto(TicketEnums.ROLE_DEMOTE_SUCCESS.getStatusCode(), TicketEnums.ROLE_DEMOTE_SUCCESS.getStatusMessage());
    	   return ResponseEntity.ok(message);
       }
		
       StatusErrorDto error = new StatusErrorDto(TicketEnums.ROLE_DEMOTE_FAILED.getStatusCode(),TicketEnums.ROLE_DEMOTE_FAILED.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PutMapping("/addRole")
	public ResponseEntity<?> addRole(@RequestParam String newRole) {
		int status = roleService.addRole(newRole);
		if(status>0) {
			SuccessMessageDto message = new SuccessMessageDto(TicketEnums.NEW_ROLE_ADD.getStatusCode(), TicketEnums.NEW_ROLE_ADD.getStatusMessage());
			return ResponseEntity.ok(message);
		}
		StatusErrorDto error = new StatusErrorDto(TicketEnums.NEW_ROLE_FAILED.getStatusCode(),TicketEnums.NEW_ROLE_FAILED.getStatusMessage());
		return ResponseEntity.badRequest().body(error);
		
	}
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PutMapping("/removeRole")
	public ResponseEntity<?> removeRole(@RequestParam String newRole) {
	int status = roleService.removeRole(newRole);
	if(status > 0) {
		SuccessMessageDto message = new SuccessMessageDto(TicketEnums.ROLE_REMOVED_MESSAGE.getStatusCode(),TicketEnums.ROLE_REMOVED_MESSAGE.getStatusMessage());
		return ResponseEntity.ok(message);
	}
	StatusErrorDto error = new StatusErrorDto(TicketEnums.ROLE_NOT_REMOVE.getStatusCode(),TicketEnums.ROLE_NOT_REMOVE.getStatusMessage());
		return ResponseEntity.badRequest().body(error);
	}
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PostMapping("/addUrl")
	public ResponseEntity<?> addUrl(@RequestBody UrlDto url) {
		int status = permissionService.addUrl(url);
		if(status>0) {
			SuccessMessageDto message = new SuccessMessageDto(TicketEnums.ADD_URL_MESSAGE.getStatusCode(),TicketEnums.ADD_URL_MESSAGE.getStatusMessage());
		    return ResponseEntity.ok(message);
		}	
		StatusErrorDto error = new StatusErrorDto(TicketEnums.ADD_URL_ERROR.getStatusCode(),TicketEnums.ADD_URL_ERROR.getStatusMessage());
		return ResponseEntity.badRequest().body(error);
	}
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PatchMapping("/removeUrl")
	public ResponseEntity<?> removeUrl(@Valid @RequestBody UrlDto url) {
		int status = permissionService.removeUrl(url);
		if(status >0) {
			SuccessMessageDto message =new SuccessMessageDto(TicketEnums.REMOVE_URL_MESSAGE.getStatusCode(),TicketEnums.REMOVE_URL_MESSAGE.getStatusMessage() );
		return ResponseEntity.ok(message);
		}	
		StatusErrorDto error = new StatusErrorDto(TicketEnums.REMOVE_URL_ERROR.getStatusCode(),TicketEnums.REMOVE_URL_ERROR.getStatusMessage());
		return ResponseEntity.badRequest().body(error);
	}
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PostMapping("/addHttpMethod")
	public ResponseEntity<?> addHttpMethod(@Valid @RequestBody HttpMethodRoleRights http) {
	int status = permissionService.addHttp(http);
	if(status >0) {
		SuccessMessageDto message =new SuccessMessageDto(TicketEnums.HTTP_METHOD_ADD.getStatusCode(),TicketEnums.HTTP_METHOD_ADD.getStatusMessage());
	return ResponseEntity.ok(message);
	}
	StatusErrorDto error = new StatusErrorDto(TicketEnums.HTTP_METHOD_ADD_FAILED.getStatusCode(),TicketEnums.HTTP_METHOD_ADD_FAILED.getStatusMessage());
	return ResponseEntity.badRequest().body(error);
	
	}
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PatchMapping("/removeHttp")
	public ResponseEntity<?> removeHttp(@Valid @RequestBody HttpMethodRoleRights http ) {
	
		int status = permissionService.removeHttpMethod(http);
		if(status >0) {
			SuccessMessageDto message =new SuccessMessageDto(TicketEnums.HTTP_METHOD_REMOVE.getStatusCode(),TicketEnums.HTTP_METHOD_REMOVE.getStatusMessage());
		return ResponseEntity.ok(message);
		}
		StatusErrorDto error = new StatusErrorDto(TicketEnums.HTTP_REMOVE_FAILED.getStatusCode(),TicketEnums.HTTP_REMOVE_FAILED.getStatusMessage());
		return ResponseEntity.badRequest().body(error);
		
		}

		
}
