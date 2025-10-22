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
import org.springframework.web.bind.annotation.RestController;

import com.classicmodels.employee.dto.EmployeeDTO;
import com.classicmodels.employee.dto.EmployeeRegistrationRequest;
import com.classicmodels.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeRegistrationRequest employee) {
		EmployeeDTO employee2 = employeeservice.add(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee2);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getEmployees(){
		List<EmployeeDTO> all = employeeservice.getAll();
		return ResponseEntity.ok(all);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> get(@PathVariable Long id) {
		EmployeeDTO employee = employeeservice.get(id);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody EmployeeRegistrationRequest updateReq) {
		EmployeeDTO employeeDTO = employeeservice.update(id,updateReq);
		return ResponseEntity.ok(employeeDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		employeeservice.delete(id);
	}
	
}
