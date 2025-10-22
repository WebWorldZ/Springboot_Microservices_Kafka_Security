package com.classicmodels.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.product.entities.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, String> {

}
