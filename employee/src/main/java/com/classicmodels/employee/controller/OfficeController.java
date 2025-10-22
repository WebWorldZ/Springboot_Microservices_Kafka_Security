package com.classicmodels.employee.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.classicmodels.employee.dto.OfficeDTO;
import com.classicmodels.employee.dto.OfficeRegistrationRequest;
import com.classicmodels.employee.service.OfficeService;

@RestController
@RequestMapping("/employees/offices")
public class OfficeController {

	@Autowired
	private OfficeService officeservice;
	
	@PostMapping
	public ResponseEntity<OfficeDTO> create(@RequestBody OfficeRegistrationRequest officeReq) {
		OfficeDTO office2 = officeservice.add(officeReq);
		return ResponseEntity.status(HttpStatus.CREATED).body(office2);
	}
	
	@GetMapping
	public ResponseEntity<List<OfficeDTO>> getOffices(){
		List<OfficeDTO> all = officeservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<List<OfficeDTO>> getOfficesWithPagination(@RequestParam Integer page, @RequestParam Integer size){
		List<OfficeDTO> all = officeservice.getAllPaginated(page,size);
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OfficeDTO> get(@PathVariable Integer id) {
		OfficeDTO office = officeservice.get(id);
		return ResponseEntity.ok(office);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OfficeDTO> update(@PathVariable Integer id, @RequestBody OfficeRegistrationRequest updateReq) {
		OfficeDTO office = officeservice.update(id,updateReq);
		return ResponseEntity.ok(office);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		officeservice.delete(id);
	}
	
}
