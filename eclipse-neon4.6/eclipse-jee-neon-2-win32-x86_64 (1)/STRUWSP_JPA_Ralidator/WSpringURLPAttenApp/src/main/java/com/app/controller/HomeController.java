package com.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.app.controller.domain.User;
import com.app.controller.model.*;
import com.app.controller.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	 @RequestMapping(value = "/fetch/{id}/{name}/", method = RequestMethod.GET)
	  public @ResponseBody String getDynamicUriValueRegex(@PathVariable("name") String name, @PathVariable("id") String id, HttpServletRequest request) {
		// Pattern pattern = Pattern.compile("[A-Z]", Pattern.CASE_INSENSITIVE);   
		 String pattst ="^[A-Z0-9]+@[A-Z0-9]+.[A-Z]+";//".*ab.*";
		//boolean matches = pattern.matcher(pattern, name);^[A-Z0-9._%+-]+@[A-Z0-9.-]"[a-zA-Z]"; 
		 
		 
		Pattern pattern = Pattern.compile(pattst, Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(name);
        boolean matches = matcher.matches();
	       
		 
		 if (matches) {
		 System.out.println(id+"  Name is " + name);
		 }
		   logger.info("----+++"+pattern.matcher(name.toString()).matches());
			logger.info("The URI -->"+request.getRequestURI());
	        return "My Dynamic test";
	    }
	//http://localhost:8080/controller/fetchs/%22%20%22/ empty id
	 @RequestMapping(value = "/fetchs/{id}/", method = RequestMethod.GET)
	  public @ResponseBody String show(@PathVariable("id") String id) {
		 
		    Optional<String> str=  Optional.ofNullable(id);
		    System.out.println(str.isPresent()+"******");
		    if (!str.isPresent() || str ==null) {
		    	System.out.println("Yes --> ID empty"+id);
		    }
		 
		    String pattst ="[A-Z0-9]*";//".*ab.*";
			 
			Pattern pattern = Pattern.compile(pattst, Pattern.CASE_INSENSITIVE);

			Matcher matcher = pattern.matcher(id);
	        boolean matches = matcher.matches();
		       
			 
			 if (matches) {
			 System.out.println(id+"  Name is " + id);
			 }
		 
	     
	        return "Dynamic URI parameter fetched using regex";
	    }
	

	 @GetMapping(path="/checkList")
		@ResponseBody
	     public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("check-User-Header", "checkuser");
		List<User> list=userService.getAllUsers();
		
		logger.info("The URI -->"+request.getRequestURI());
		  logger.info("Welcome home! The client locale is {}."+list);
		  logger.info("Accept : --> " + acceptType);
			logger.info("The URI -->"+request.getRequestURI());
			
		  System.out.println("=== Request data ===");
			java.util.Enumeration reqEnum = request.getAttributeNames();
			while (reqEnum.hasMoreElements()) {
				String s = (String) reqEnum.nextElement();
				
				System.out.println(s);
					
			}
	  	
		return new ResponseEntity<List<User>>(list, headers, HttpStatus.OK);
		
	   }
	 @GetMapping(path="/getProduct/{id}")
	 @ResponseBody
	 public ResponseEntity<Product> findOneProduct(@PathVariable("id") Integer id, HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		   Product product=userService.findProductById(id);
		   HttpHeaders headers = new HttpHeaders();
		     headers.add("check-Product-Header", "product"); 
		   
		  return ResponseEntity.ok(product); 
	 }
	 /*
	  *  @PutMapping(value="/updated/{id}", consumes = {"text/plain", "application/json"}, produces = {"text/plain", "application/json"})
public ResponseEntity<User> updatedUser(@RequestBody User user,@PathVariable
	  * ResponseEntity<Void>
	  */
	// @RequestMapping(value="/updateUser/{id}", method = RequestMethod.PUT, consumes = {"text/plain", "application/json"}, produces = {"text/plain", "application/json"})
	 @PutMapping(value="/updateUser/{id}", consumes = {"text/plain", "application/json"}, produces = {"text/plain", "application/json"})
	 public ResponseEntity<Object> updatedUser(@RequestBody User user, @PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response,
			                                                           @RequestHeader(value="Accept") String acceptType){
		  // User user1 =new User();  //userService.findOneById(id);
		   user.setId(id);
		   //user.setEmail("MyChange@show.com");
		   //user1.setName("Test Change");
		   HttpHeaders headers = new HttpHeaders();
	        headers.add("User-Header", "updateuser");  //header name, header value
	       
		   userService.updatedUser(user);
		   response.setHeader("1", headers.getFirst("User-Header"));

		   logger.info("The URI -->"+request.getRequestURI());
			  logger.info("Welcome home! The client locale is {}."+user);
			  logger.info("Accept : --> " + acceptType);
				logger.info("The URI -->"+request.getRequestURI());
		 
		  // model.addAttribute("User", user);
		return ResponseEntity.noContent().build();
		//return "redirect:/show";
				//return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
				
				//return new ResponseEntity<>(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value = "/show", method = RequestMethod.GET)
		public String show() {
			
			return "updated";
	}
	 
	 @GetMapping(path="/getDepartment")
		@ResponseBody
	     public ResponseEntity<List<Department>> getDepartmentList(HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("check-department-Header", "checkdepartment");
		List<Department> list=userService.getDepartments();
		
		logger.info("The URI -->"+request.getRequestURI());
		  logger.info("Welcome home! The client locale is {}."+list);
		  logger.info("Accept : --> " + acceptType);
			logger.info("The URI -->"+request.getRequestURI());
			
		 /* System.out.println("=== Request data ===");
			java.util.Enumeration reqEnum = request.getAttributeNames();
			while (reqEnum.hasMoreElements()) {
				String s = (String) reqEnum.nextElement();
				
				System.out.println(s);
					
			}*/
	  	
		return new ResponseEntity<List<Department>>(list, headers, HttpStatus.OK);
		
	   }
	 
	 @GetMapping(path="/getEmployees")
		@ResponseBody
	     public ResponseEntity<List<Employee>> getEmployeeList(HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("check-employee-Header", "checkemployee");
		List<Employee> list=userService.getAllEmployees();
		
		
		logger.info("The URI -->"+request.getRequestURI());
		  logger.info("Welcome home! The client locale is {}."+list);
		  logger.info("Accept : --> " + acceptType);
			logger.info("The URI -->"+request.getRequestURI());
			
		 /* System.out.println("=== Request data ===");
			java.util.Enumeration reqEnum = request.getAttributeNames();
			while (reqEnum.hasMoreElements()) {
				String s = (String) reqEnum.nextElement();
				
				System.out.println(s);
					
			}*/
	  	
		return new ResponseEntity<List<Employee>>(list, headers, HttpStatus.OK);
		
	   }
	 @PostMapping(path="/addEmployees", consumes = {"text/plain", "application/json"}, produces = {"text/plain", "application/json"})
	 public ResponseEntity<Void>  saveAlls(@Validated @RequestBody Employee employee,  
			                                                    HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("check-employee-Header", "checkemployee");
	     
		   userService.saveAlls(employee);
		
		
		logger.info("The URI -->"+request.getRequestURI());
		  
		  logger.info("Accept : --> " + acceptType);
			logger.info("The URI -->"+request.getRequestURI());
			
			return ResponseEntity.noContent().build();
	 }
	 @PostMapping(path="/addDepartEmps", consumes = {"text/plain", "application/json"}, produces = {"text/plain", "application/json"})
	 public ResponseEntity<Void> addAllEmpDepart(@Validated @RequestBody Employee employee, @Validated @RequestBody Department department,
			 HttpServletRequest request, @RequestHeader(value="Accept") String acceptType){
		
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("check-employee-Header", "checkemployee");
	     
		   userService.addAlls(employee, department);//(employee, department);
		
		
		logger.info("The URI -->"+request.getRequestURI());
		  
		  logger.info("Accept : --> " + acceptType);
			logger.info("The URI -->"+request.getRequestURI());
			
			return ResponseEntity.noContent().build();
	 }
}

