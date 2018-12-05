package com.app.controller.service;

import java.util.List;

import com.app.controller.model.Department;
import com.app.controller.model.Employee;
import com.app.controller.model.Product;
import com.app.controller.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.controller.dao.*;


@Service
public class UserServiceImpl implements UserService {

	UserDao userDao;
	 //@Required
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	@Transactional
	public void addUser(User user){
		userDao.addUser(user);
	}
	@Override
	@Transactional
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	@Override
	@Transactional
	public void updatedUser(User user){
		userDao.updatedUser(user);
	}
	@Override
	@Transactional
	public User findOneById(Integer id){
		return userDao.findOneById(id);
	}
	@Override
	@Transactional
	public void detetdOneById(Integer id){
		userDao.detetdOneById(id);
	}
	@Override
	@Transactional
	public List<User> findByEmail(String email){
		
		return userDao.findByEmail(email);
	}
	@Override
	@Transactional
	public List<Product> getAllProducts(){
		return userDao.getAllProducts();
	}
	@Override
	@Transactional
	public Product findProductById(Integer id){
		
		return userDao.findProductById(id);
	}
	@Override
	@Transactional
     public List<Employee> getAllEmployees(){
    	 return userDao.getAllEmployees();
     }
	@Override
	@Transactional
	public List<Department> getDepartments(){
		
		return userDao.getDepartments();
	}
	@Override
	@Transactional
	public void saveAlls(Employee employee){
		userDao.saveAlls(employee);
	}
	@Override
	@Transactional
	public void addAlls(Employee employee, Department department){
		userDao.addAlls(employee, department);
	}
}
