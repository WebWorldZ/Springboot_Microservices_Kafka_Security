package com.classicmodels.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classicmodels.order.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
