package com.isteer.jdbc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import com.isteer.jdbc.enums.TicketEnums;
import com.isteer.jdbc.exception.RegisterFailedException;
import com.isteer.jdbc.model.Role;
import com.isteer.jdbc.model.UserDetailsSerect;
@Component
public class CustomerSecurityRepository {
	 @Autowired
	    JdbcTemplate template;
	    @Autowired
	    NamedParameterJdbcTemplate jdbctemplate;
	    @Autowired
	    PlatformTransactionManager transactionManager;
	    
	    public UserDetailsSerect findByUserName(String userName) {
//	    	System.out.println("find userby name.......");
	    	System.out.println(userName);
	    	String sql = "SELECT userId, userName,password FROM users WHERE userName = :userName";
	    	String roleSql = "SELECT r.roleId, r.roleName FROM roles r INNER JOIN userroles ur ON r.roleId = ur.roleId WHERE ur.userId = :userId";
	    	MapSqlParameterSource param = new MapSqlParameterSource()
	    			.addValue("userName", userName);
	    	UserDetailsSerect user = new UserDetailsSerect();
	    	try {
	    		user = jdbctemplate.queryForObject(sql, param, new BeanPropertyRowMapper<>(UserDetailsSerect.class));
	    	  
	    	} catch (EmptyResultDataAccessException e) {   
//	    		throw new UsernameNotFoundException(TicketEnums.USER_NOT_FOUND.getStatusMessage());  
	    	} 
	    	if(user == null) {
	    		System.out.println("username null");
	    	throw new UsernameNotFoundException("User Name not Found");
	    }
	    param.addValue("userId", user.getUserId());
	    List<Role> roles = jdbctemplate.query(roleSql, param, new BeanPropertyRowMapper<>(Role.class));
	    user.setRoles(roles);
//	    System.out.println("userid "+user.getUserId());
		return user;
		
	    }

	    public int registerUser(UserDetailsSerect user) {
	        String insertUser = "INSERT INTO users (userName, password) VALUES (:userName, :password)";
	        String previousId = "SELECT LAST_INSERT_ID()";
	        String assignUserRole = "INSERT INTO userroles(userId, roleId) VALUES (:userId, :roleId)";

	        SqlParameterSource params = new MapSqlParameterSource()
	                .addValue("userName", user.getUserName())
	                .addValue("password", user.getPassword());

	        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
	        return transactionTemplate.execute(status -> {
	            try {
	                // Insert the user
	                jdbctemplate.update(insertUser, params);

	                // Retrieve the generated user ID
	                int userId = template.queryForObject(previousId, Integer.class);

	                // Insert roles for the user
	                for (Role role : user.getRoles()) {
	                    SqlParameterSource roleParams = new MapSqlParameterSource()
	                            .addValue("userId", userId)
	                            .addValue("roleId", role.getRoleId());
	                    jdbctemplate.update(assignUserRole, roleParams);
	                }

	                // If everything is successful, return user ID
	                return userId;
	            } catch (Exception e) {
	                status.setRollbackOnly();  // Marks the transaction for rollback in case of error
	                throw new RegisterFailedException(TicketEnums.REGISTERATION_FAILED);
	            }
	        });
	    }
	    
	    public int elevateRole(String userName, int roleId) {
	        String findUserId = "SELECT userId FROM users WHERE userName = :userName";
	        String elevateRole = "INSERT INTO userRoles (userId, roleId) VALUES (:userId, :roleId)";

	        SqlParameterSource findUserParams = new MapSqlParameterSource()
	                .addValue("userName", userName);

	        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

	        return transactionTemplate.execute(status -> {
	            try {
	                // Get the userId from the userName
	                int userId = jdbctemplate.queryForObject(findUserId, findUserParams, Integer.class);

	                // Insert the new role for the user
	                SqlParameterSource elevateRoleParams = new MapSqlParameterSource()
	                        .addValue("userId", userId)
	                        .addValue("roleId", roleId);

	                jdbctemplate.update(elevateRole, elevateRoleParams);

	                return 1;
	            } catch (Exception e) {
	                status.setRollbackOnly();  // Mark the transaction for rollback on error
	                return 0;
	            }
	        });
	    }
	    
	    public int deleteRole(String userName, int roleId) {
	        String findUserId = "SELECT userId FROM users WHERE userName = :userName";
	        String deleteRole = "DELETE FROM userroles WHERE roleId = :roleId AND userId = :userId";

	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue("userName", userName);

	        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
	        try {
	            int userId = jdbctemplate.queryForObject(findUserId, params, Integer.class);
	          
	            params.addValue("userId", userId);
	            params.addValue("roleId", roleId);

	            jdbctemplate.update(deleteRole, params);
	            transactionManager.commit(status);

	            return 1;
	        } catch (Exception e) {
	            transactionManager.rollback(status);
	            e.printStackTrace();
	            return 0;
	        }
	    }
      

}
