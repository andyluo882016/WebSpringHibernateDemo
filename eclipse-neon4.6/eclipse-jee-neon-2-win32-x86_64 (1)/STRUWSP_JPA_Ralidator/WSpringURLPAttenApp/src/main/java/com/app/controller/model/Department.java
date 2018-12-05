package com.app.controller.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Department")
public class Department {
   
	@Id
	@Column(name="department_id")//department_id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long dId;
	@Column(name="dept_name")
	private String dName;
	@Column(name="dept_address")//dept_address
	private String dAddress;
	
	public long getdId() {
		return dId;
	}

	public void setdId(long dId) {
		this.dId = dId;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdAddress() {
		return dAddress;
	}

	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}
	
	//@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    //private List<Employee> employees;

	/*public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Department [dId=" + dId + ", dName=" + dName + ", dAddress=" + dAddress + ", employee=" + employee
				+ "]";
	}*/
	
	

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Department [dId=" + dId + ", dName=" + dName + ", dAddress=" + dAddress + "]";
	}
	 
	 
}
