package com.classicmodels.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
