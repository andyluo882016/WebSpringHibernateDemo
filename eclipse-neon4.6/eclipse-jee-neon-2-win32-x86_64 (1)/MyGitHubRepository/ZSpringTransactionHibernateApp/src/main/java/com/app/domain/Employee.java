package com.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.app.domain.*;
/******************************
 * The join column is declared with the @JoinColumn annotation which looks like the @Column annotation. It has one more parameters 
 * named referencedColumnName. This parameter declares the column in the targeted entity that will be used to the join.
* If no @JoinColumn is declared on the owner side, the defaults apply. A join column(s) will be created in the owner table and its name will be the 
* concatenation of the name of the relationship in the owner side, _ (underscore), and the name of the primary key column(s) in the owned side.
* In a bidirectional relationship, one of the sides (and only one) has to be the owner. The owner is responsible for the association column(s) update. 
* To declare a side as not responsible for the relationship, the attribute mappedBy is used. ‘mappedBy’ refers to the property name of the association 
* on the owner side.
 * 
 * 
 */

@Entity
@Table(name="Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="employee_id")
	private Integer id;
	
	@Column(name="first_name")
	private String fname;
	
	@Column(name="last_name")
	private String lname;
	
	private Integer salary;
	
	//@JoinColumn defines foreign key column and indicates the owner of the relationship.
	@OneToOne  //(cascade = CascadeType.ALL) @JoinColumn(name="ACCOUNT_ID")
	@JoinColumn(name="employee_id")
	private Address address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Employee() {
		
	}

	public Employee(Integer id, String fname, String lname, Integer salary, Address address) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.salary = salary;
		this.address = address;
		//this.address.setEmployee(this);
	}
	
	
		
    	


}
