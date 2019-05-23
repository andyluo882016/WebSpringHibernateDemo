package com.app.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.app.domain.User;
import com.app.exception.UserNotFoundByIdException;

public interface UserManager {

	public void setDataSource(DataSource ds);

	public void setName(int id, String name);
	public int deleteUser(Integer id) throws UserNotFoundByIdException;
	 
   // public List<User> getAllUser();
	public List<Map<String, Object>> getList() throws UserNotFoundByIdException;
    public User getById(Integer id)throws UserNotFoundByIdException;
    
    public void saveOne(User user);
    public void saveOne2(User user);
}
