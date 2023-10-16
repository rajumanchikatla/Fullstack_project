package com.jalaacademy.project.service;

import java.util.List;

import com.jalaacademy.project.model.Employee;

public interface Employeeservice {

	    Employee createEmployee(Employee employee);
	  
	    Employee getEmployee(long mobileNumber);
	    
	    List<Employee> getAllEmployees();
	    
	    Employee updateEmployee(long mobileNumber, Employee updatedEmployee);
	    
		List<Employee> findByFirstNameOrLastName(String firstName, String lastName);
	    
	    void deleteEmployee(long mobileNumber);
}
