package com.classicmodels.order.service;

import java.util.List;

import com.classicmodels.order.dto.OrderDTO;
import com.classicmodels.order.dto.OrderRegistrationRequest;

public interface OrderService {

	OrderDTO add(OrderRegistrationRequest req);
	
	List<OrderDTO> getAll();
	
	OrderDTO get(Long id);
	
	OrderDTO update(Long id,OrderRegistrationRequest req);
	
	void delete(Long id);
}
