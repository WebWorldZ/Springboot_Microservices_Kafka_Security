package com.classicmodels.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.customer.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
