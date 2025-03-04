package com.isteer.jdbc.repository;

import java.util.List;

import com.isteer.jdbc.model.Empolyee;

public interface TicketManagementDao {
	
	public int createTicket(Empolyee ticket);
	public List<Empolyee> getTicketByUser(String createdBy);
	public int assignTicket(Integer ticketId, String assignedTo);
	public int updateStatus(Integer ticketId, String status);
	public Empolyee getTicketById(Integer ticketId);
	public List<Empolyee> getAllTickets();
	
	
	

}
