package com.jalaacademy.project.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.jalaacademy.project.model.Employee;

@Repository
public interface Employeedao extends JpaRepositoryImplementation<Employee,Long> {
	

	List<Employee> findByFirstNameOrLastName(String firstName, String lastName);


}
