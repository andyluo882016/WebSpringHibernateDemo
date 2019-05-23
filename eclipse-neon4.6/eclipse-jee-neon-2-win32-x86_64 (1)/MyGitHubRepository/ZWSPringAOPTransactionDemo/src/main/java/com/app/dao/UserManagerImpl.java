package com.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.app.domain.User;
import com.app.domain.UserMapper;
import com.app.exception.UserIsExsitingException;
import com.app.exception.UserNotFoundByIdException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class UserManagerImpl implements UserManager{

	 private JdbcTemplate jdbcTemplate;
	 
	 public void setDataSource(DataSource dataSource) {
		   
	      this.jdbcTemplate = new JdbcTemplate(dataSource);
	   }
	
     @Override
	 @Transactional(rollbackFor=UserNotFoundByIdException.class) 
	public User getById(Integer id)throws DataAccessException{
    	
        String query = "select * from User where id = ?";
        User user =null;
		try {
		 user = jdbcTemplate.queryForObject(query, new Object[]{id}, new UserMapper());
		
		}catch(DataAccessException e){
			System.out.println("User is not found by the ID -->>"+id);
			System.out.println("***-->"+e.getLocalizedMessage());
			if (user ==null) {
		    System.out.println("Catch exception message as below:\n");
			throw new UserNotFoundByIdException(id);
			}
		}catch(Exception e)  
        {  
            System.out.println("Parent Exception occurs");  
           }      
		return user;
		
     }
     //(required = false)
	@Override
	@Transactional(rollbackFor=UserIsExsitingException.class, propagation = Propagation.REQUIRES_NEW)
    public void saveOne(User user) throws UserIsExsitingException{
    	String inserQuery = "insert into user (id, email, name) values (?, ?, ?) ";
    	int m=0;
		   try {
		    m= jdbcTemplate.update(inserQuery, new Object[] { user.getId(),
	                 user.getEmail(), user.getName()  
	        });
		   }catch(DataAccessException e) {
			   System.out.println("User is already exsiting by the ID -->>"+user.getId());
				System.out.println("***-->"+e.getLocalizedMessage());
				if ( m !=1 || m==0) {
					logException(new UserIsExsitingException(user.getId()));
				   // throw new UserIsExsitingException(user.getId());
				    
					}
		   }
		  
			
		   System.out.println("\nDone!!!");
		 
			  
		  
		  
    }
	
	private static void logException(UserIsExsitingException e) {
	      System.out.println("-- exception message --");
	      System.err.println(e.getMessage());
	      System.out.println("----- Done for Exception  ----");
	  }
	
	@Override
	 @Transactional(rollbackFor=UserNotFoundByIdException.class) 
	public List<Map<String, Object>> getList() {
	    return this.jdbcTemplate.queryForList("select * from user");
	}
	
	@Override
	 @Transactional(rollbackFor=UserNotFoundByIdException.class, propagation = Propagation.REQUIRES_NEW) 
	 public void setName(int id, String name) throws UserNotFoundByIdException{
		
	       int t=this.jdbcTemplate.update("update user set name = ? where id = ?", name, id);
	       if (t !=1) {
	    	   System.out.println("---  The user by id is not found ---");
	    	   throw new UserNotFoundByIdException(id);
	       }
	       else
	    	   System.out.println("The user by id is updated ");
	    }
	
	@Override
	 @Transactional(rollbackFor=UserNotFoundByIdException.class, propagation = Propagation.REQUIRES_NEW) 
	public int deleteUser(Integer id){
		int t=this.jdbcTemplate.update("delete from user where id = ?", id);
		if (t !=1) {
			 System.out.println("---  The user by id is not found ---");
	    	   throw new UserNotFoundByIdException(id);
		}
		else
			System.out.println("The user is deleted by the id: "+id);
	    return t;
	}
	
	@Override
	@Transactional(rollbackFor=UserIsExsitingException.class, propagation = Propagation.REQUIRES_NEW)
    public void saveOne2(User user) throws UserIsExsitingException{
    	String inserQuery = "insert into user (id, email, name) values (?, ?, ?) ";
		  
		    int m= jdbcTemplate.update(inserQuery, new Object[] { user.getId(),
	                 user.getEmail(), user.getName()  
	        });
		  
		  
			if ( m !=1 || m==0) {
			logException(new UserIsExsitingException(user.getId()));
		    throw new UserIsExsitingException(user.getId());
		    
			}
		   System.out.println("\n-------  >"+m);
		 
			  
		  
		  
    }
	 
}
