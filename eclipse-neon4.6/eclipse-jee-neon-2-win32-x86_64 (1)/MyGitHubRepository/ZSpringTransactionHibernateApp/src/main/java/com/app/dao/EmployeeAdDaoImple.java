package com.app.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.app.domain.Address;
import com.app.domain.Employee;

public class EmployeeAdDaoImple implements EmployeeAdDao{

	/*private SessionFactory sessionfactory;
	
	
    public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}*/

    
    private SessionFactory sessionFactory;

	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    	
	@Override
	@Transactional(rollbackFor=ObjectIsexistException.class)
	public void save(Employee ep) throws ObjectIsexistException{
		//Integer eid=ad.getEmployee_id();
		Session session =sessionFactory.openSession();
		Transaction tx=null;
		try {
		tx=session.beginTransaction();
		//session.persist(ep);
		Integer id=(Integer)session.save(ep);
		if (id ==null)
			throw new ObjectIsexistException(id);
		System.out.println("----  "+id);
		//System.out.println("++_-"+session.get(Employee.class, 8));
		System.out.println("*** is saveing yet?? ");//+session.save(ep));
		//session.persist(ep);
		System.out.println("** statues: *+++"+session.isOpen());
		//session.flush();
		tx.commit();
		}catch(Exception e){
			System.out.println("****^^^^***  "+e.getMessage());
			tx.rollback();
			System.out.println("*** The transaction local status is: "+tx.getLocalStatus());
			//throw e;
		}finally{
			session.close();
			//logger.info("The transaction is: {}.", tx.isActive());
			System.out.println("The transaction is: "+tx.isActive());
			}
		
	}
	@Override
	@Transactional(rollbackFor=ObjectIsexistException.class)
	public void saveEmp(Employee ep){
		Session session=null;
		Transaction tx=null;
		try{
			session=sessionFactory.getCurrentSession();
			tx=session.beginTransaction();
			session.save(ep);
			System.out.println("---The transaction is: "+tx.getLocalStatus());
			tx.commit();
			System.out.println("+++++The transaction is: "+tx.isActive());
		}catch(DataAccessException e){
			System.out.println("^^^****--> "+e.getMessage());
			tx.rollback();
			throw e;
		}catch(Exception e) {
			System.out.println("*****++++++  "+e.toString());
		}finally{
			session.close();
			//logger.info("The transaction is: {}.", tx.isActive());
			System.out.println("__===The transaction is: "+tx.isActive());
			}
	}
	
	@Transactional(rollbackFor=DuplecateKeyUserException.class)
	public void saveEmpAddress(Employee emp) {
		
		Session session=null;
		Transaction tx=null;
		try {
		session =sessionFactory.openSession();
		System.out.println("Step 1 : "+session.getStatistics());
		tx=session.beginTransaction();
		System.out.println("Step 2 : "+session.getStatistics());
		Integer tm =(Integer)session.save(emp);
		if (tm ==null || tm==0 )
			throw new DuplecateKeyUserException(emp.getId());
		tx.commit();
		System.out.println("setp show 3 :"+tm.toString());
		}
		catch(Exception e) {
			System.out.println("****^^^^***  "+e.getMessage());
			tx.rollback();
			System.out.println("*** The transaction local status is: "+tx.getLocalStatus());
			
		}finally{
			
			session.close();
		}
		
		
	}
	@Override
	@Transactional
	public List<Object[]> getAlls() {
		Session session=null;
		Transaction tx=null;
		List<Object[]> elist =new ArrayList<Object[]>();
		try {
		  session =sessionFactory.openSession();
		  tx=session.beginTransaction();
		  //tx.begin();
		 // System.out.println("transaction has saving point: "+tx.isInitiator());
		  System.out.println("transaction has saving point: "+tx.isInitiator());
		  //String query ="from employee";
		  //query = session.createSQLQuery("select e.emp_id, emp_name, emp_salary,address_line1, city, 
		//	zipcode from Employee e, Address a where a.emp_id=e.emp_id");
		  //elist=session.createQuery(query).list();
		  SQLQuery myquery = session.createSQLQuery("select employee_id, first_name, last_name, salary from employee");//.addEntity(Employee.class);
		  List<Object[]> rows = myquery.list();
		  System.out.println("transaction is active point: "+tx.isActive());
		  elist=rows;
		  tx.commit();
		  
		}catch (Exception e) {
			System.out.println("****^^^^***  "+e.getMessage());
			tx.rollback();
			System.out.println("*** The transaction local status is: "+tx.getLocalStatus());
		}finally{
			
			session.close();
		}
		return elist;
	}
	
}
