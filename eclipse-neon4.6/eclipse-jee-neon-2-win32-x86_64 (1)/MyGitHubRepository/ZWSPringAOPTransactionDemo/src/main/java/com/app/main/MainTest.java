package com.app.main;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.dao.UserManager;
import com.app.domain.User;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		
		UserManager umanager=(UserManager) context.getBean("userManager");
		
		//User user=umanager.getUserById(10);
		User user=umanager.getById(123);
		
		System.out.println(" ---- User : -->"+user.toString());
		
		/*List<Map<String, Object>> lists=umanager.getList();
		
		Map<String, Object> map=lists.get(123);
		System.out.println(map.entrySet());*/
		
		//umanager.setName(120, "Makrer Poller");
		
		User person=new User(122, "MyExstingTest@test.org", "isExsiting Test8");
		umanager.saveOne(person);
		
		//umanager.setName(129, "Makrer Poller");
		//umanager.deleteUser(123);
		
	}

}
