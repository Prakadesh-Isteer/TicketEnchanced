package com.isteer.jdbc.dto;

public class TicketDto {
	
	private Integer ticketId;
	private String ticketHeading;
    private String ticketDescription;
    private String createdBy;
    private String assignedTo;
    private String status;
    
	public String getTicketHeading() {
		return ticketHeading;
	}
	public void setTicketHeading(String ticketHeading) {
		this.ticketHeading = ticketHeading;
	}
	public String getTicketDescription() {
		return ticketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}


}
