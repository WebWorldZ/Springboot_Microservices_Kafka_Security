package com.classicmodels.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.employee.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
