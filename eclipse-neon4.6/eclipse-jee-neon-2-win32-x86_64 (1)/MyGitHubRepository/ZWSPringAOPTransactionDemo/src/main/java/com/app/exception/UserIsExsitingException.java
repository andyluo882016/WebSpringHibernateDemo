package com.app.exception;

import org.springframework.dao.DataAccessException;

public class UserIsExsitingException extends DataAccessException{

private static final long serialVersionUID = 1L;
	
	public UserIsExsitingException(Integer id) {
		super("***** --> User is already exsiting by the ID: "+id);
	}
}
