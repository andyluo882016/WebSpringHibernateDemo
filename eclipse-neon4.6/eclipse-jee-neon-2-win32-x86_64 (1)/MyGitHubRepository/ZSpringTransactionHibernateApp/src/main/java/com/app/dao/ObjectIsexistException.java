package com.app.dao;

public class ObjectIsexistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4535430590963691328L;
	
	public ObjectIsexistException(Integer id){
		//super("the Object is already exsiting");
		super("***User Is already Exist Exception with id  ***="+id);
		System.out.println("The Object with ID--> "+id +" is already exsiting");
	}

}
