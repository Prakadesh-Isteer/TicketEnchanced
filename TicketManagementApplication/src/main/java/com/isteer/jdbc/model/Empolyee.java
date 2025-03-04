package com.isteer.jdbc.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
public class Empolyee {
	
	private Integer ticketId;
	@NotBlank(message = "TICKET HEADING CANNOT BE EMPTY")
	private String ticketHeading;
	@NotBlank(message = "TICKET DESCRIPTION CANNOT BE EMPTY")
    private String ticketDescription;  
    private String createdBy;
    private LocalDateTime createdDate;
    private String assignedTo = "To be assigned";
    private String status = "Opened";
    
    @Override
	public String toString() {
		return "Empolyee [ticketId=" + ticketId + ", ticketHeading=" + ticketHeading + ", ticketDescription="
				+ ticketDescription + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", assignedTo="
				+ assignedTo + ", status=" + status + "]";
	}
    
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
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