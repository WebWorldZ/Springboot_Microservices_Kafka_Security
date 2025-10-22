package com.classicmodels.employee.service;

import java.util.List;

import com.classicmodels.employee.dto.EmployeeDTO;
import com.classicmodels.employee.dto.EmployeeRegistrationRequest;

public interface EmployeeService {
	
	EmployeeDTO add(EmployeeRegistrationRequest employee);
	
	List<EmployeeDTO> getAll();
	
	EmployeeDTO get(Long id);
	
	EmployeeDTO update(Long id,EmployeeRegistrationRequest req);
	
	void delete(Long id);
}
