package com.app.dao;

import org.springframework.dao.DataAccessException;

public class DuplecateKeyUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplecateKeyUserException(Integer id){
		super("***User Is already Exist Exception with id  ***="+id);
		System.out.println("*** ---> The Object with ID--> "+id +" is already exsiting");
	}
}
