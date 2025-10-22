package com.classicmodels.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.order.entities.Product;

public interface ProductBackupRepository extends JpaRepository<Product, String> {

}
