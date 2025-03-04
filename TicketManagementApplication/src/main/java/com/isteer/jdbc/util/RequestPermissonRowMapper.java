package com.isteer.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.isteer.jdbc.model.RequestPermission;

public class RequestPermissonRowMapper implements RowMapper<RequestPermission> {

	@Override
	public RequestPermission mapRow(ResultSet rs, int rowNum) throws SQLException {
		RequestPermission permission = new RequestPermission();
		permission.setUrlPattern(rs.getString("url"));
		permission.setRoleId(rs.getInt("roleId"));
		return permission;
	}

}
