package com.app.controller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Employee")
public class Employee {
	@Id
	@Column(name="employee_id")//employee_id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long eId;
	@Column(name="first_name")//first_name
	private String fname;
	@Column(name="last_name")//last_name
	private String lname;
	
	@Column(name="email")
	private String email;
	@Column(name="cell_phone")//cell_phone
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="department_id",foreignKey=@ForeignKey(name="department_id_FK"))//FK_DEPT
	private Department department;
	
	public long geteId() {
		return eId;
	}

	public void seteId(long eId) {
		this.eId = eId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone="
				+ phone + ", department=" + department + "]";
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
