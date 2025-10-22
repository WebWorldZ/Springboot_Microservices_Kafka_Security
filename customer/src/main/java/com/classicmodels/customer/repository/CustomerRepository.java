package com.classicmodels.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
