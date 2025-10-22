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

import com.classicmodels.order.dto.OrderDetailDTO;
import com.classicmodels.order.dto.OrderDetailRegistrationRequest;
import com.classicmodels.order.service.OrderDetailService;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderdetailservice;
	
	@PostMapping
	public ResponseEntity<OrderDetailDTO> create(@RequestBody OrderDetailRegistrationRequest orderdetail) {
		OrderDetailDTO orderDetailDTO = orderdetailservice.add(orderdetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<List<OrderDetailDTO>> getOrderDetails(){
		List<OrderDetailDTO> all = orderdetailservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailDTO> get(@PathVariable Long id) {
		OrderDetailDTO orderDetailDTO = orderdetailservice.get(id);
		return ResponseEntity.ok(orderDetailDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDetailDTO> update(@PathVariable Long id, @RequestBody OrderDetailRegistrationRequest updateReq) {
		OrderDetailDTO orderDetailDTO = orderdetailservice.update(id,updateReq);
		return ResponseEntity.ok(orderDetailDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		orderdetailservice.delete(id);
	}
}
