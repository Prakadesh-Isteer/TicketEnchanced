package com.isteer.jdbc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.isteer.jdbc.model.HttpMethodRoleRights;
import com.isteer.jdbc.model.RequestPermission;
import com.isteer.jdbc.util.HttpMethodRowMapper;
import com.isteer.jdbc.util.RequestPermissonRowMapper;
@Component
public class RequestPermissionRepo {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<RequestPermission> findUrl(String url){
		String findUrl = "SELECT requestId, url, roleId FROM permission WHERE url = :url";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("url", url);
		List<RequestPermission> accessible = jdbcTemplate.query(findUrl, param, new RequestPermissonRowMapper());
		return accessible;
	}
	
	public List<HttpMethodRoleRights> findHttpMethod(String httpMethod){
		String findHttp = "SELECT httpMethod, roleId FROM httpmethod WHERE httpMethod = :httpMethod";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("httpMethod", httpMethod);
		List<HttpMethodRoleRights> roles = jdbcTemplate.query(findHttp, param, new HttpMethodRowMapper());
		return roles;
	}
	
	public int addUrl(String url, int roleId) {
		String addUrl = "INSERT INTO permission(url,roleId) VALUES(:url, :roleId)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("url",url)
				.addValue("roleId", roleId);
		int status = jdbcTemplate.update(addUrl, param);
		return status;
	}
	
	public int removeUrl(String url, int roleId) {
		String addUrl = "DELETE FROM permission WHERE url=:url AND roleId = :roleId";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("url",url)
				.addValue("roleId", roleId);
		int status = jdbcTemplate.update(addUrl, param);
		return status;
	}
//	----------------------------------------------------------------------------------------------
	public int addHttpMethod(String httpMethod, int roleId) {
		String addHttpMethod = "INSERT INTO httpmethod(httpMethod,roleId) VALUES (:httpMethod, :roleId)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("httpMethod", httpMethod)
				.addValue("roleId", roleId);
		return jdbcTemplate.update(addHttpMethod, param);
	}
	
	public int removeHttpMethod(String httpMethod, int roleId) {
		String removeHttpmethod = "DELETE FROM httpmethod WHERE httpMethod= :httpMethod AND roleId = :roleId";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("httpMethod", httpMethod).addValue("roleId", roleId);
		return jdbcTemplate.update(removeHttpmethod, param);
		
	}

}
