package com.classicmodels.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.customer.entities.Employee;

public interface EmployeeBackupRepository extends JpaRepository<Employee, Long> {

}
