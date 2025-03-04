package com.isteer.jdbc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isteer.jdbc.dto.StatusErrorDto;
import com.isteer.jdbc.dto.SuccessMessageDto;
import com.isteer.jdbc.enums.TicketEnums;
import com.isteer.jdbc.model.Empolyee;
import com.isteer.jdbc.service.TicketManagementService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/appSteer")
public class TicketManagementController {
	
	public static  Logger logging = LogManager.getLogger(TicketManagementController.class);
	@Autowired
	TicketManagementService service;
	
	@PreAuthorize("@requestPermissionService.hasPermission()")
	@PostMapping("/raiseticket")
	public ResponseEntity<?> raiseTicket(@Validated @RequestBody Empolyee ticket) {
		logging.info("Raise ticket endpoint hitted");
		int status = service.createTicket(ticket);
		if(status == 1) {
			SuccessMessageDto message = new SuccessMessageDto(TicketEnums.TICKET_SUBMITTED_SUCCESSFULLY.getStatusCode(), TicketEnums.TICKET_SUBMITTED_SUCCESSFULLY.getStatusMessage());
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		StatusErrorDto error = new StatusErrorDto(TicketEnums.TICKET_FAILED.getStatusCode(),TicketEnums.TICKET_FAILED.getStatusMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


        @PreAuthorize("@requestPermissionService.hasPermission()")
	    @GetMapping("/getTickets")
	    public List<?> getTicketsForUser() {
        	logging.info("getting ticket endpoint hitted");
	        return service.getTicketByUser();
	    }
	    
    @PreAuthorize("@requestPermissionService.hasPermission()")
	    @GetMapping("/getTicketDetails")
	    public ResponseEntity<?> ticketDetails(@Valid @RequestParam Integer ticketId) {
    	logging.info("getTicketDetails endpoint hitted");
	    	Empolyee employee = new Empolyee();
	        employee = service.getTicketById(ticketId);
	        if (employee != null) {
	            return ResponseEntity.ok(employee);
	        }
	        StatusErrorDto error = new StatusErrorDto(TicketEnums.USER_NOT_FOUND.getStatusCode(),TicketEnums.USER_NOT_FOUND.getStatusMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    }

    @PreAuthorize("@requestPermissionService.hasPermission()")
	    @PostMapping("/assign")
	    public ResponseEntity<?> assignTicket(@RequestParam int ticketId, @RequestParam String assignTo) {
    	logging.info("Assign ticket endpoint hitted");
	        int status = service.assignTicket(ticketId, assignTo);
	        if (status > 0) {
	         SuccessMessageDto message = new SuccessMessageDto(TicketEnums.TICKET_ASSIGNED.getStatusCode(), TicketEnums.TICKET_ASSIGNED.getStatusMessage());
				return ResponseEntity.status(HttpStatus.OK).body(message);
	        }

	        StatusErrorDto error = new StatusErrorDto(TicketEnums.TICKET_NOT_ASSIGED.getStatusCode(),TicketEnums.TICKET_NOT_ASSIGED.getStatusMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	    }
	    
      @PreAuthorize("@requestPermissionService.hasPermission()")
	    @PostMapping("/setStatus")
	    public ResponseEntity<?> setTicketStatus(@RequestParam Integer ticketId, 
	                                                  @RequestParam String ticketStatus) {
    	  logging.info("set status ticket endpoint hitted");
	        int status = service.ticketStatusUpdate(ticketId, ticketStatus);
	        if (status > 0) {
	        	 SuccessMessageDto message = new SuccessMessageDto(TicketEnums.STATUS_SET.getStatusCode(), TicketEnums.STATUS_SET.getStatusMessage());
					return ResponseEntity.status(HttpStatus.OK).body(message);
	        }
	        StatusErrorDto error = new StatusErrorDto(TicketEnums.STATUS_NOT_SET.getStatusCode(),TicketEnums.STATUS_NOT_SET.getStatusMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	    }
      
      @PreAuthorize("@requestPermissionService.hasPermission()")
      @GetMapping("getAllTickets")
      public List<?> getAlltickets() { 
    	  System.out.println("in this controller all the tickets will be shown");
    	  logging.info("Getting all ticket endpoint hitted");
	        return service.getAllTickets();
      }
      
      
	    
	
}
