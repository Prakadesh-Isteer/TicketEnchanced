package com.isteer.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.isteer.jdbc.model.HttpMethodRoleRights;

public class HttpMethodRowMapper implements RowMapper<HttpMethodRoleRights> {

	@Override
	public HttpMethodRoleRights mapRow(ResultSet rs, int rowNum) throws SQLException {
		HttpMethodRoleRights permission = new HttpMethodRoleRights();
		permission.setHttpMethod(rs.getString("httpMethod"));
		permission.setRoleId(rs.getInt("roleId"));
		return permission;
	}

}
