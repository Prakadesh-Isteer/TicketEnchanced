package com.isteer.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.isteer.jdbc.model.Role;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
    Role role = new Role();
    role.setRoleId(rs.getInt("roleId"));
    role.setRoleName(rs.getString("roleName"));
		return role;
	}

}
