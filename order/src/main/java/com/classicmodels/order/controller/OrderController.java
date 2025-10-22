package com.classicmodels.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classicmodels.order.dto.OrderDTO;
import com.classicmodels.order.dto.OrderRegistrationRequest;
import com.classicmodels.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderservice;
	
	@PostMapping
	public ResponseEntity<OrderDTO> create(@RequestBody OrderRegistrationRequest order) {
		OrderDTO orderDTO = orderservice.add(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getOrders(){
		List<OrderDTO> all = orderservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> get(@PathVariable Long id) {
		OrderDTO orderDTO = orderservice.get(id);
		return ResponseEntity.ok(orderDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody OrderRegistrationRequest updateReq) {
		OrderDTO orderDTO = orderservice.update(id,updateReq);
		return ResponseEntity.ok(orderDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		orderservice.delete(id);
	}
}
