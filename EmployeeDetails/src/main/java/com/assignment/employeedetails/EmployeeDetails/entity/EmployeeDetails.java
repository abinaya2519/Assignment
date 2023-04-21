package com.assignment.employeedetails.EmployeeDetails.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_details")
public class EmployeeDetails {
	
	@Id
	@Column(name="employee_id")
	private Integer id;
	
	@Column(name="employee_name")
	private Integer employeeName;
	
	@Column(name="first_name")
	private Integer firstName;
	
	@Column(name="last_name")
	private Integer lastName;
	
	@Column(name="project_name")
	private Integer projectName;
	
	@Column(name="email_address")
	private Integer emailAddress;
	
	@Column(name="contact")
	private Integer contact;
	
	@Column(name="country")
	private String country;
	
	@Column(name="channel")
	private String channel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(Integer employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getFirstName() {
		return firstName;
	}

	public void setFirstName(Integer firstName) {
		this.firstName = firstName;
	}

	public Integer getLastName() {
		return lastName;
	}

	public void setLastName(Integer lastName) {
		this.lastName = lastName;
	}

	public Integer getProjectName() {
		return projectName;
	}

	public void setProjectName(Integer projectName) {
		this.projectName = projectName;
	}

	public Integer getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(Integer emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getContact() {
		return contact;
	}

	public void setContact(Integer contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
	
	
	

}
