package com.isteer.jdbc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.isteer.jdbc.model.Empolyee;

@Component
public class TicketRepository implements TicketManagementDao{
	
	@Autowired
	JdbcTemplate template;
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private BeanPropertyRowMapper<Empolyee> rowMapper = new BeanPropertyRowMapper<>(Empolyee.class);

	@Override
	public int createTicket(Empolyee ticket) {
       String sql = "INSERT INTO employee (ticketHeading, ticketDescription, createdBy, createdDate, assignedTo, status) VALUES (:ticketHeading, :ticketDescription,:createdBy, :createdDate, :assignedTo, :status)";
       SqlParameterSource param = new MapSqlParameterSource()
    		   .addValue("ticketHeading", ticket.getTicketHeading())
    		   .addValue("ticketDescription", ticket.getTicketDescription())
    		   .addValue("createdBy", ticket.getCreatedBy())
    		   .addValue("createdDate", ticket.getCreatedDate())
    		   .addValue("assignedTo", ticket.getAssignedTo())
    		   .addValue("status",ticket.getStatus());
		return jdbcTemplate.update(sql,param);
		
		
	}

	@Override
	public List<Empolyee> getTicketByUser(String createdBy) {
	String sql = "SELECT ticketId, ticketHeading, ticketDescription, createdBy, createdDate, assignedTo, status FROM employee WHERE createdBy = :createdBy";
	SqlParameterSource param = new MapSqlParameterSource()
			.addValue("createdBy",createdBy);
	List<Empolyee> ticket = jdbcTemplate.query(sql,param, rowMapper);
		return ticket;
	}

	@Override
	public int assignTicket(Integer ticketId, String assignedTo) {
		String assignTicket = "UPDATE employee SET status = :status, assignedTo = :assignedTo WHERE ticketId = :ticketId";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("ticketId",ticketId)
				.addValue("status", "Assigned")
				.addValue("assignedTo",assignedTo);
		return jdbcTemplate.update(assignTicket, param);
	}

	@Override
	public Empolyee getTicketById(Integer ticketId) {
		String sql = "SELECT ticketId, ticketHeading, ticketDescription, createdBy, createdDate, assignedTo, status  FROM  employee WHERE ticketId = :ticketId";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("ticketId", ticketId);
		return jdbcTemplate.queryForObject(sql, param, rowMapper);
	}

	@Override
	public int updateStatus(Integer ticketId, String status) {
		String sql = "UPDATE employee SET status = :status WHERE ticketId = :ticketId";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("ticketId", ticketId)
				.addValue("status", status);
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public List<Empolyee> getAllTickets() {
		String sql = "SELECT ticketId, ticketHeading, ticketDescription, createdBy, createdDate, assignedTo, status  FROM  employee";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	

}
