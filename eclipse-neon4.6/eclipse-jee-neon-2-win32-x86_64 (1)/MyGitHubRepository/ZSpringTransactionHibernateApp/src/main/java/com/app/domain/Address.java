package com.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="employee_id")
	private Integer id;
	
	@Column(name="street_name")
	private String street_name;
	
	@Column(name="city_name")
	private String city_name;
	
	@Column(name="state_name")
	private String state_name;
	
	@Column(name="zipcode")
	private String zipcode;
	
	//mappedBy indicates the inverse of the relationship.
	@OneToOne(mappedBy="address") //FK_address_employee  FOREIGN KEY
	private Employee employee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Address(){}
	public Address(String street_name, String city_name, String state_name, String zipcode) {
		super();
		this.street_name = street_name;
		this.city_name = city_name;
		this.state_name = state_name;
		this.zipcode = zipcode;
		
	}

	public Address(Integer id, String street_name, String city_name, String state_name, String zipcode) {
		super();
		this.id = id;
		this.street_name = street_name;
		this.city_name = city_name;
		this.state_name = state_name;
		this.zipcode = zipcode;
		//this.employee = employee;
	}
	
	
	
}
