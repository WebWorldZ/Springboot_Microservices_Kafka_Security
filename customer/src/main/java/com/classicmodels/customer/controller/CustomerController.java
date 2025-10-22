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

import com.classicmodels.customer.dto.CustomerDTO;
import com.classicmodels.customer.dto.CustomerRegistrationRequest;
import com.classicmodels.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@PostMapping
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerRegistrationRequest customer) {
		CustomerDTO customerDTO = customerservice.add(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getCustomers(){
		List<CustomerDTO> all = customerservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> get(@PathVariable Long id) {
		CustomerDTO customerDTO = customerservice.get(id);
		return ResponseEntity.ok(customerDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerRegistrationRequest updateReq) {
		CustomerDTO customerDTO = customerservice.update(id,updateReq);
		return ResponseEntity.ok(customerDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		customerservice.delete(id);
	}

}
