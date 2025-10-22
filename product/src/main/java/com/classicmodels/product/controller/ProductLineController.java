package com.classicmodels.product.controller;

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

import com.classicmodels.product.dto.ProductLineDTO;
import com.classicmodels.product.service.ProductLineService;

@RestController
@RequestMapping("/productlines")
public class ProductLineController {

	@Autowired
	private ProductLineService productlineservice;
	
	@PostMapping
	public ResponseEntity<ProductLineDTO> create(@RequestBody ProductLineDTO productLine) {
		ProductLineDTO productLineDTO = productlineservice.add(productLine);
		return ResponseEntity.status(HttpStatus.CREATED).body(productLineDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<List<ProductLineDTO>> getCustomers(){
		List<ProductLineDTO> all = productlineservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{productline}")
	public ResponseEntity<ProductLineDTO> get(@PathVariable String productline) {
		ProductLineDTO productLineDTO = productlineservice.get(productline);
		return ResponseEntity.ok(productLineDTO);
	}
	
	@PutMapping("/{productline}")
	public ResponseEntity<ProductLineDTO> update(@PathVariable String productline, @RequestBody ProductLineDTO updateReq) {
		ProductLineDTO productLineDTO = productlineservice.update(productline,updateReq);
		return ResponseEntity.ok(productLineDTO);
	}
	
	@DeleteMapping("/{productline}")
	public void delete(@PathVariable String productline) {
		productlineservice.delete(productline);
	}
}
