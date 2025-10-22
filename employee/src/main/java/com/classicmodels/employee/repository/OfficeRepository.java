package com.classicmodels.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.employee.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, Integer>{

}
