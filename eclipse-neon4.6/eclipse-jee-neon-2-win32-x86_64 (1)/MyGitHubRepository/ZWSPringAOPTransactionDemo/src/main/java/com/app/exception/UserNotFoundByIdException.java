package com.app.exception;

import org.springframework.dao.DataAccessException;

public class UserNotFoundByIdException extends DataAccessException{

private static final long serialVersionUID = 1L;
	
	public UserNotFoundByIdException(Integer id) {
		super("+++++++ -->> User is not found by the ID: "+id);
	}
}
