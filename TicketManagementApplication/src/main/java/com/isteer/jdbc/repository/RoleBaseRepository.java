package com.isteer.jdbc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.isteer.jdbc.model.Role;
import com.isteer.jdbc.util.RoleRowMapper;

@Component
public class RoleBaseRepository {
	
	@Autowired
	JdbcTemplate template;
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;
	
	public List<Role> getAllRoles(){
		String sql ="SELECT roleId, roleName from roles";
		BeanPropertyRowMapper<Role> rowMapper = new BeanPropertyRowMapper<>(Role.class);
		List<Role> roles = template.query(sql, rowMapper);
		return roles;
	}
	
	public int getRoleId(String roleName) {
		String getRoleId = "SELECT roleId, roleName FROM roles WHERE roleName = :roleName";
		MapSqlParameterSource param = new MapSqlParameterSource()
				.addValue("roleName", roleName);
		Role fetchRole = namedTemplate.queryForObject(getRoleId,param,new RoleRowMapper());
		return fetchRole.getRoleId();
	}
	
	public int addRole(String setRole) {
		System.out.println("Add role repo");
		String insertRole = "INSERT INTO roles(roleName) VALUES(:roleName)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roleName", setRole);
		int status = namedTemplate.update(insertRole, param);
		return status;
	}
	
	public int removeRole(String removeRole) {
		String sql = "DELETE FROM roles WHERE roleName = :roleName";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("roleName", removeRole);
		return namedTemplate.update(sql, param);
		
	}

//	--------------------------------------------------------------
	public int getRoleIdInt(int roleId) {
		String getRoleId = "SELECT roleId, roleName FROM roles WHERE roleId = :roleId";
		MapSqlParameterSource param = new MapSqlParameterSource()
				.addValue("roleId", roleId);
		Role fetchRole = namedTemplate.queryForObject(getRoleId,param,new RoleRowMapper());
		return fetchRole.getRoleId();
	}

}
