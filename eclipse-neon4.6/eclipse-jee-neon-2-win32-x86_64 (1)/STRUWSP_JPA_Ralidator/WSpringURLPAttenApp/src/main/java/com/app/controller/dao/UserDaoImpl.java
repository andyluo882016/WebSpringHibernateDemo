package com.app.controller.dao;

import java.util.List;
import javax.persistence.TypedQuery; 
import com.app.controller.model.Department;
import com.app.controller.model.Employee;
import com.app.controller.model.Product;
import com.app.controller.model.User;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
@Scope("prototype")
@Repository
public class UserDaoImpl implements UserDao{

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(User user){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Integer t=(Integer) session.save(user);
		tx.commit();
		logger.info("User saved successfully, Person Details="+t);
		session.close();
	}
	@Override
	public List<User> getAllUsers(){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> list=session.createQuery("from User").list();
		tx.commit();
		logger.info("User List get complated successfully, Person Details=");
		session.close();
		return list;
	}
	@Override
	public void updatedUser(User user){
		Session session = this.sessionFactory.openSession();
		logger.info("The databases connection has been creted");
		Transaction tx = session.beginTransaction();
		logger.info("begin transaction --> "+tx.isActive());
		session.update(user);
		
		logger.info("the transaction status -- >"+tx.wasCommitted());
		tx.commit();
		logger.info("User updated successfully, Person Details=  "+user.toString());
		logger.info("after commit **> the transaction status -- >"+tx.isActive());
		logger.info("*** **> the transaction status -- >"+tx.wasCommitted());
		session.close();
		logger.info("the transaction status -- >"+tx.isActive());
		
	}
	@Override
	public User findOneById(Integer id){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user =(User) session.get(User.class, id);
		tx.commit();
		logger.info("User has been found successfully, Person Details="+user);
		session.close();
		return user;
	}
	@Override
	public void detetdOneById(Integer id){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user =(User) session.get(User.class, id);
		session.delete(user);
		logger.info("User has been deleted successfully, Person Details="+user);
		tx.commit();
		session.close();
	}
	@Override
	public List<User> findByEmail(String email){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		logger.info("User List get **********  Details="+tx.getLocalStatus());
		
		 Query query = session.createQuery("from User Where email = :email");
		 logger.info("User Session get **********  Details="+tx.getLocalStatus());
        query.setParameter("email", email);
        logger.info("User Session get ******^^^^  Details="+tx.getLocalStatus());
       // session.flush();
        List<User> mlist = query.list();
        logger.info("User Session get ****++++++^  Details="+query.list());
        tx.commit();
		session.close();
		logger.info("User Session get ^^^^^  Details="+tx.getLocalStatus());
		return mlist;
	}
	@Override
	public List<Product> getAllProducts(){
		Session session= sessionFactory.openSession();
		logger.info("Got connect to Product databases");
		Transaction tx=session.beginTransaction();
		logger.info("User List get **********  Details="+tx.getLocalStatus());
		List<Product> list=session.createQuery("from Product").list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public Product findProductById(Integer id){
		Session session= sessionFactory.openSession();
		logger.info("Got connect to Product databases");
		Transaction tx=session.beginTransaction();
		logger.info("Product Session get ^^^^^  Details="+tx.getLocalStatus());
		Product prodcut =(Product) session.get(Product.class, id);
		logger.info("Product Session get ^^+^^^  Details="+tx.getLocalStatus());
		tx.commit();
		logger.info("Product Session get ^^++^^^  Details="+tx.getLocalStatus());
			session.close();
			logger.info("Product Session get ^^^^^+++  Details="+tx.getLocalStatus());
	    return prodcut;
	}
	
	@Override
     public List<Employee> getAllEmployees(){
    	 Session session= sessionFactory.openSession();
    	 logger.info("Got connect to Product databases");
 		Transaction tx=session.beginTransaction();
 		logger.info("Employee Session get ^^^^^  Details="+tx.getLocalStatus());
 		logger.info("The List-->",session.getStatistics());
 		// List<Employee> list=session.createSQLQuery("from Employee as e where e.department_id = :department_id").list();
 		
 		@SuppressWarnings("unchecked")
 		//TypedQuery<Employee> query= session.createQuery("from Employee");    
 		
 		 List<Employee> list= session.createQuery("from Employee").list();
 	   //List<Txn1> lis= (List<Txn1>) session.createQuery("from transaction").list();
 	   for(Object c : list)
 		   System.out.println(c.toString());
 		
 	    //List<Employee> list=query.getResultList();    
 		
 		logger.info("Employee  %% Session get ^^^^^  Details="+tx.getLocalStatus());
 		logger.error("****>"+list.toString());
 		
 		 
 		logger.info("Employee List get complated successfully, Employee Details=");
 		tx.commit();
		session.close();
		return list;
     }
	@Override
	public List<Department> getDepartments(){
		
		Session session= sessionFactory.openSession();
     	 logger.info("Got connect to Product databases");
		Transaction tx=session.beginTransaction();
		logger.info("Product Session get ^^^^^  Details="+tx.getLocalStatus());
		
		// TypedQuery query=(TypedQuery) session.createQuery("from Department");    
		  //  List<Department> list=query.getResultList();  
		@SuppressWarnings("unchecked")
		List<Department> list= session.createQuery("from department").list();
	 	   //List<Txn1> lis= (List<Txn1>) session.createQuery("from transaction").list();
	 	   for(Object c : list)
	 		   System.out.println(c.toString());
		
		 //List<Department> list=session.createSQLQuery("from Department").list();
		logger.info("Department List get complated successfully, Department Details=");
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public void saveAlls(Employee employee){
		 Session session = null;
	
		//Session session= sessionFactory.openSession();
		Transaction tx=null;
		 try {
		 session= sessionFactory.openSession();
		  tx = session.beginTransaction();
		 logger.info("Got connect to Product databases");
		  Department depart=employee.getDepartment();
		   session.save(depart);
		   logger.info(tx.isActive()+ "  Department Session saving get ----  Details="+tx.getLocalStatus());
		   employee.setDepartment(depart);
		    session.save(employee);
		//employee.setDepartment(department);
		//session.save(employee);
		logger.info("Employee saving Session get ----  Details="+tx.getLocalStatus());
 		logger.info("The List-***->",session.getStatistics());
		tx.commit();
		logger.info("Employee Session get ^^^^^  Details="+tx.getLocalStatus());
 		logger.info("The List-->",session.getStatistics());
		logger.info("User saved successfully, Person Details=");
		//tx.isParticipating()
		 } catch (Exception e) {
			 logger.info("The Transaction -->"+tx.isActive());
		      if (tx !=null) {
		        tx.rollback();
		      }
		      e.printStackTrace();
		    } finally {
		      if (session != null) {
		        session.close();
		      }
		    }
		
	}
	//not working below one
	@Override
	public void addAlls(Employee employee, Department department){
Session session= sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		 logger.info("Got connect to Product databases");
		 Integer t=(Integer) session.save(department);
		 employee.setDepartment(department);
	      session.save(employee);
	      logger.info("Employee Session get ----  Details="+tx.getLocalStatus());
	 		logger.info("The List-->",session.getStatistics());
			tx.commit();
			logger.info("Employee Session get ^^^^^  Details="+tx.getLocalStatus());
	 		logger.info("The List-->",session.getStatistics());
			logger.info("User saved successfully, Person Details="+t);
			session.close();
	}
}
