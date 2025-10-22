package com.classicmodels.customer.service;

import java.util.List;

import com.classicmodels.customer.dto.PaymentDTO;
import com.classicmodels.customer.dto.PaymentRegistrationRequest;

public interface PaymentService {

	PaymentDTO add(PaymentRegistrationRequest req);
	
	List<PaymentDTO> getAll();
	
	PaymentDTO get(Long id);
	
	PaymentDTO update(Long id,PaymentRegistrationRequest req);
	
	void delete(Long id);
}
