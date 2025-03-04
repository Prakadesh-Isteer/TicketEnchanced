package com.isteer.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.isteer.jdbc.model.UserDetailsSerect;

@Component
public class UserRepository {

	    @Autowired
	    private NamedParameterJdbcTemplate template;

	    public UserDetailsSerect findByUsername(String userName) {
	        String sql = "SELECT userId,userName,password,role FROM users WHERE username = :username";
	        SqlParameterSource params = new MapSqlParameterSource("username", userName);
	        return template.queryForObject(sql, params, new BeanPropertyRowMapper<>(UserDetailsSerect.class));
	    }

	    public void save(UserDetailsSerect user) {
	        String sql = "INSERT INTO users (username, password ) VALUES (:username, :password)";
	        SqlParameterSource params = new MapSqlParameterSource()
	                .addValue("username", user.getUserName())
	                .addValue("password", user.getPassword());
	        template.update(sql, params);
	    }
	}


