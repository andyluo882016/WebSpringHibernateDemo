package com.app.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

	private Integer id;
	private String email;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(Integer id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	
	
}
