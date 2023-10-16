package com.jalaacademy.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@ToString
	public class Employee {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@Id
	private long mobileNumber;
	
	private String dob;
	
	private String gender;
	
	private String country;
	
	private String city;
	
	}
