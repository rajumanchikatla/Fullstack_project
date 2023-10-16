package com.jalaacademy.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jalaacademy.project.model.Employee;
import com.jalaacademy.project.service.Employeeservice;


@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class Employeecontroller {
	
	   private Employeeservice employeeService;

	    @Autowired
	    public Employeecontroller(Employeeservice employeeService) {
	        this.employeeService = employeeService;
	    }
	    @PostMapping
	    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
	        try {
	            Employee createdEmployee = employeeService.createEmployee(employee);
	            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	        } catch (Exception e) {
	            // Log the exception for debugging purposes
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("mobile/{mobileNumber}")
	    public ResponseEntity<Employee> getEmployee(@PathVariable long mobileNumber) {
	        Employee employee = employeeService.getEmployee(mobileNumber);
	        if (employee != null) {
	            return new ResponseEntity<>(employee, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @GetMapping("searchByName")
	    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String name) {
	        if (name == null || name.trim().isEmpty()) {
	            return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.BAD_REQUEST);
	        } else {
	            String[] nameParts = name.split(" ");
	            if (nameParts.length >= 2) {
	                String firstName = nameParts[0];
	                String lastName = nameParts[1];
	                List<Employee> employees = employeeService.findByFirstNameOrLastName(firstName, lastName);
	                if (!employees.isEmpty()) {
	                    return new ResponseEntity<>(employees, HttpStatus.OK);
	                } else {
	                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	                }
	            } else {
	                // Handle searching by either first name or last name
	                List<Employee> employees = employeeService.findByFirstNameOrLastName(name, name);
	                if (!employees.isEmpty()) {
	                    return new ResponseEntity<>(employees, HttpStatus.OK);
	                } else {
	                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	                }
	            }
	        }
	    }

	    @GetMapping
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	        List<Employee> employees = employeeService.getAllEmployees();
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }

	    @PutMapping("/{mobileNumber}")
	    public ResponseEntity<Employee> updateEmployee(
	            @PathVariable long mobileNumber,
	            @RequestBody Employee updatedEmployee) {
	        Employee updated = employeeService.updateEmployee(mobileNumber, updatedEmployee);
	        if (updated != null) {
	            return new ResponseEntity<>(updated, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    @DeleteMapping("/{mobileNumber}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable long mobileNumber) {
	        employeeService.deleteEmployee(mobileNumber);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }    
}
