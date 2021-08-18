package com.work.task.sbjpa.model;

import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name="employee_test")
public class EmployeeTest {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long employeeId;
	@Column(name="first_name")
	private String FirstName;
	@Column(name="last_name")
	private String LastName;
	@Column(name="display_name")
	private String DisplayName;	
	@Column(name="email")
	private String Email;
	
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	
	
	
	
}
