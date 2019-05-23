package com.app.dao.main;
import java.util.*;

import javax.persistence.Entity;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.dao.*;
import com.app.domain.*;
public class MainTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ObjectIsexistException {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("EmployeeAd.xml");
		
		EmployeeAdDao employeeAdDao=context.getBean(EmployeeAdDao.class);
		
		Address adress=new Address(14,"678 Flower Dr", "Boomingto", "KH", "89709");
		
		Employee emp=new Employee(14,"Keven-1","Oliver-1",88880,adress);
		
		
		//employeeAdDao.save(emp);
		//employeeAdDao.saveEmpAddress(emp);
		//employeeAdDao.saveEmp(emp);
		
	  List<Object[]> elist=	employeeAdDao.getAlls();
	  
	  
	  int i=0;
	   for(Object[] e : elist) {
		   //System.out.println("---"+e.equals("Address"));
		  System.out.println(e[0].toString()+" "+e[1].toString()+" "+e[2].toString()+" "+e[3].toString());
	   }
	   System.out.println("-------\n");
	   
	   for(Object[] row : elist){
			Employee empd = new Employee();
			//i++;
			empd.setId(Integer.parseInt(row[0].toString()));
			empd.setFname(row[1].toString());
			empd.setLname(row[2].toString());
			empd.setSalary(Integer.parseInt(row[3].toString()));
			System.out.println(empd.getLname().toString());
		}
	   
	}

}
