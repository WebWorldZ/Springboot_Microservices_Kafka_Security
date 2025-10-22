package com.classicmodels.customer.service;

import java.util.List;

import com.classicmodels.customer.dto.CustomerDTO;
import com.classicmodels.customer.dto.CustomerRegistrationRequest;

public interface CustomerService {

	CustomerDTO add(CustomerRegistrationRequest customer);
	
	List<CustomerDTO> getAll();
	
	CustomerDTO get(Long id);
	
	CustomerDTO update(Long id,CustomerRegistrationRequest req);
	
	void delete(Long id);
}
