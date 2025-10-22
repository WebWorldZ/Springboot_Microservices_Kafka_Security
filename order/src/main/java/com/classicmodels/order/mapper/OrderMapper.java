package com.classicmodels.order.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.CustomerEvent;
import com.classicmodels.order.dto.OrderDTO;
import com.classicmodels.order.dto.OrderRegistrationRequest;
import com.classicmodels.order.entities.Customer;
import com.classicmodels.order.entities.Order;
import com.classicmodels.order.repository.CustomerBackupRepository;

@Service
public class OrderMapper {

	@Autowired
	private CustomerBackupRepository customerbackuprepo;
	
	public OrderDTO mapToOrderDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO(
				order.getOrderNumber(),
				order.getOrderDate(),
				order.getRequiredDate(),
				order.getShippedDate(),
				order.getStatus(),
				order.getComments(),
				order.getCustomer().getCustomerNumber()
				);
		return orderDTO;
	}
	
	public Order mapToOrder(OrderRegistrationRequest req) {
		Customer customerNumber = null; 
		if(req.customerNumber()!=null) {
			customerNumber = customerbackuprepo.findById(req.customerNumber()).orElse(null);
		}
		Order order = new Order();
		order.setOrderDate(req.orderDate());
		order.setRequiredDate(req.requiredDate());
		order.setShippedDate(req.shippedDate());
		order.setStatus(req.status());
		order.setComments(req.comments());
		order.setCustomer(customerNumber);
		return order;
	}
	
	public Customer mapEventToCustomer(CustomerEvent event) {
		Customer customer = new Customer(
				event.customerNumber(),
				event.customerName(),
				event.contactLastName(),
				event.contactFirstName(),
				event.phone(),
				event.addressLine1(),
				event.addressLine2(),
				event.city(),
				event.state(),
				event.postalCode(),
				event.country(),
				event.creditlimit()
				);
		return customer; 
	}
	
}
