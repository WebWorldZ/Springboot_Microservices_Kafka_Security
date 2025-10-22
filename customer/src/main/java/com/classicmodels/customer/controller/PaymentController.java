package com.classicmodels.customer.controller;

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

import com.classicmodels.customer.dto.PaymentDTO;
import com.classicmodels.customer.dto.PaymentRegistrationRequest;
import com.classicmodels.customer.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentservice;
	
	@PostMapping
	public ResponseEntity<PaymentDTO> create(@RequestBody PaymentRegistrationRequest payment) {
		PaymentDTO paymentDTO = paymentservice.add(payment);
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<List<PaymentDTO>> getPayments(){
		List<PaymentDTO> all = paymentservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDTO> get(@PathVariable Long id) {
		PaymentDTO paymentDTO = paymentservice.get(id);
		return ResponseEntity.ok(paymentDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentRegistrationRequest updateReq) {
		PaymentDTO paymentDTO = paymentservice.update(id,updateReq);
		return ResponseEntity.ok(paymentDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		paymentservice.delete(id);
	}
}
