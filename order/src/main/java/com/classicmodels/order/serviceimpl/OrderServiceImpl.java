package com.classicmodels.order.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.order.dto.OrderDTO;
import com.classicmodels.order.dto.OrderRegistrationRequest;
import com.classicmodels.order.entities.Customer;
import com.classicmodels.order.entities.Order;
import com.classicmodels.order.mapper.OrderMapper;
import com.classicmodels.order.repository.CustomerBackupRepository;
import com.classicmodels.order.repository.OrderRepository;
import com.classicmodels.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private CustomerBackupRepository customerbackuprepo;

	@Override
	public OrderDTO add(OrderRegistrationRequest req) {
		Order order = orderMapper.mapToOrder(req);
		Order saved = orderRepo.save(order);
		OrderDTO orderDTO = orderMapper.mapToOrderDTO(saved);
		return orderDTO;
	}

	@Override
	public List<OrderDTO> getAll() {
		return orderRepo.findAll()
				.stream()
				.map(e-> orderMapper.mapToOrderDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public OrderDTO get(Long id) {
		return orderRepo.findById(id)
				.map(e-> orderMapper.mapToOrderDTO(e))
				.orElseThrow(()-> new RuntimeException("Order with order number "+id+" not found"));
	}

	@Override
	public OrderDTO update(Long id, OrderRegistrationRequest req) {
		Order orderDB = orderRepo.findById(id)
		.orElseThrow(()-> new RuntimeException("Order with order number "+id+" not found"));
		if (req.orderDate()!= null && req.orderDate().compareTo(orderDB.getOrderDate()) != 0) {
			orderDB.setOrderDate(req.orderDate());
	        }
		if (req.requiredDate()!= null && req.requiredDate().compareTo(orderDB.getRequiredDate()) != 0) {
			orderDB.setRequiredDate(req.requiredDate());
	        }
		if (req.shippedDate()!= null && req.shippedDate().compareTo(orderDB.getShippedDate()) != 0) {
			orderDB.setShippedDate(req.shippedDate());
	        }
		if (req.status()!= null && !req.status().equals(orderDB.getStatus())) {
			orderDB.setStatus(req.status());
	        }
		if (req.comments()!= null && !req.comments().equals(orderDB.getComments())) {
			orderDB.setComments(req.comments());
	        }
		if (req.customerNumber()!= null) {
			Customer customerNumber = customerbackuprepo.findById(req.customerNumber()).orElseThrow(()-> new RuntimeException("Customer with customer number "+req.customerNumber()+" not found"));
			 if(orderDB.getCustomer() == null) {
				 orderDB.setCustomer(customerNumber);
			 }else if(orderDB.getCustomer() != null && !req.customerNumber().equals(orderDB.getCustomer().getCustomerNumber())){
				 orderDB.setCustomer(customerNumber);
			 }
	        }
		Order saved = orderRepo.save(orderDB);
		OrderDTO orderDTO = orderMapper.mapToOrderDTO(saved);
		return orderDTO; 
		
	}

	@Override
	public void delete(Long id) {
		orderRepo.deleteById(id);
	}

}
