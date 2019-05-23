package com.app.dao;
import java.util.List;

import com.app.domain.*;
public interface EmployeeAdDao {

	public List<Object[]> getAlls();
	
	public void save(Employee ep) throws ObjectIsexistException;
	
	public void saveEmp(Employee ep);
	
	public void saveEmpAddress(Employee emp);
	
 	
}
