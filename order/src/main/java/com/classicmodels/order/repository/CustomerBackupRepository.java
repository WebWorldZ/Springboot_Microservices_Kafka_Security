package com.classicmodels.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.order.entities.Customer;

public interface CustomerBackupRepository extends JpaRepository<Customer, Long>{

}
