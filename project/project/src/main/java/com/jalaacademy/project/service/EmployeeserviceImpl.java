package com.jalaacademy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jalaacademy.project.Dao.Employeedao;
import com.jalaacademy.project.model.Employee;


@Service
public class EmployeeserviceImpl implements Employeeservice {

	
    private Employeedao employeedao;

    @Autowired
    public EmployeeserviceImpl(Employeedao employeedao) {
        this.employeedao = employeedao;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeedao.save(employee);
    }

    @Override
    public Employee getEmployee(long mobileNumber) {
        return employeedao.findById(mobileNumber).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeedao.findAll();
    }

    @Override
    public Employee updateEmployee(long mobileNumber, Employee updatedEmployee) {
        Employee existingEmployee = getEmployee(mobileNumber);
        if (existingEmployee != null) {
            // Update the properties you want to change
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDob(updatedEmployee.getDob());
            existingEmployee.setGender(updatedEmployee.getGender());
            existingEmployee.setCountry(updatedEmployee.getCountry());
            existingEmployee.setCity(updatedEmployee.getCity());
            // Save the updated employee
            return employeedao.save(existingEmployee);
        }
        return null; // Employee not found
    }

		    @Override
		    public void deleteEmployee(long mobileNumber) {
		    	employeedao.deleteById(mobileNumber);
		    }

			@Override
			public List<Employee> findByFirstNameOrLastName(String firstName, String lastName) {
				// TODO Auto-generated method stub
				 return employeedao.findByFirstNameOrLastName(firstName, lastName);
			}


	 
}
