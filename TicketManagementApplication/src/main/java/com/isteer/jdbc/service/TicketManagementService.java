package com.isteer.jdbc.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.isteer.jdbc.model.Empolyee;
import com.isteer.jdbc.model.UserDetailsSerect;
import com.isteer.jdbc.repository.CustomerSecurityRepository;
import com.isteer.jdbc.repository.TicketManagementDao;
@Service
public class TicketManagementService {
	
     
	@Autowired
	TicketManagementDao repo;
	@Autowired
	CustomerSecurityRepository userRepo;
	
	public int createTicket(Empolyee ticket) {
		ticket.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		ticket.setCreatedDate(LocalDateTime.now());
		return repo.createTicket(ticket);
	}
	
	public List<Empolyee> getTicketByUser(){
		String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Empolyee> ticket = repo.getTicketByUser(createdBy);
		return ticket;
		
	}
	public List<Empolyee> getAllTickets(){
		return repo.getAllTickets();
	}
	
	public Empolyee getTicketById(Integer ticketId) {
		return repo.getTicketById(ticketId);
		
	}
	
	public int assignTicket(Integer ticketId ,String assignedTo) {
		UserDetailsSerect user = userRepo.findByUserName(assignedTo);
		if(user == null) 
			return 0;	
		boolean roleStatus = false;
		if(user.getRoles().size() == 1) {
			roleStatus = user.getRoles().stream().anyMatch(role -> role.getRoleName().equalsIgnoreCase("User"));
		}
		if(!roleStatus) {
			int status = repo.assignTicket(ticketId, assignedTo);
			return status;
		}
		return 0;
	}
	
	public int ticketStatusUpdate(Integer ticketId, String status) {
		return repo.updateStatus(ticketId, status);
	}
	
	

}
