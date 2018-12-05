package com.app.controller.service;

import java.util.List;

import com.app.controller.model.Department;
import com.app.controller.model.Employee;
import com.app.controller.model.Product;
import com.app.controller.model.User;

public interface UserService {

	public void addUser(User user);
	public List<User> getAllUsers();
	public void updatedUser(User user);
	public User findOneById(Integer id);
	public void detetdOneById(Integer id);
	public List<User> findByEmail(String email);
	//-------
	
	public List<Product> getAllProducts();
	public Product findProductById(Integer id);
	
	//---------
    public List<Employee> getAllEmployees();
	
	public List<Department> getDepartments();
	
	public void saveAlls(Employee employee);
	
	public void addAlls(Employee employee, Department department);
}
