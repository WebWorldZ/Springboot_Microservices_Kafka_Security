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

import com.classicmodels.product.dto.ProductDTO;
import com.classicmodels.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productservice;
	
	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
		ProductDTO productDTO = productservice.add(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getCustomers(){
		List<ProductDTO> all = productservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{productcode}")
	public ResponseEntity<ProductDTO> get(@PathVariable String productcode) {
		ProductDTO productDTO = productservice.get(productcode);
		return ResponseEntity.ok(productDTO);
	}
	
	@PutMapping("/{productcode}")
	public ResponseEntity<ProductDTO> update(@PathVariable String productcode, @RequestBody ProductDTO updateReq) {
		ProductDTO productDTO = productservice.update(productcode,updateReq);
		return ResponseEntity.ok(productDTO);
	}
	
	@DeleteMapping("/{product}")
	public void delete(@PathVariable String product) {
		productservice.delete(product);
	}
}
