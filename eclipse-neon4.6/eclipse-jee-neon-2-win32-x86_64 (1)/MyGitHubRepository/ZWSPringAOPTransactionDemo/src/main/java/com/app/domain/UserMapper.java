package com.app.domain;

import org.springframework.jdbc.core.RowMapper;

//import com.app.dao.NoUserFoundByIdException;
import com.app.exception.UserNotFoundByIdException;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{

	public User mapRow(ResultSet rs, int rowNum) throws UserNotFoundByIdException, SQLException {
	      User user = new User();
	      
	      user.setId(rs.getInt("id"));
	      
	      user.setEmail(rs.getString("email"));
	      user.setName(rs.getString("name"));
	    

	      return user;
	   }
}
