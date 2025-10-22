package com.classicmodels.order.service;

import java.util.List;

import com.classicmodels.order.dto.OrderDetailDTO;
import com.classicmodels.order.dto.OrderDetailRegistrationRequest;

public interface OrderDetailService {

	OrderDetailDTO add(OrderDetailRegistrationRequest req);
	
	List<OrderDetailDTO> getAll();
	
	OrderDetailDTO get(Long id);
	
	OrderDetailDTO update(Long id,OrderDetailRegistrationRequest req);
	
	void delete(Long id);
}
