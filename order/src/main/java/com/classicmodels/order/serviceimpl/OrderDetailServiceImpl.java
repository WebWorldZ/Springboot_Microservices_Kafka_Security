package com.classicmodels.order.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.order.dto.OrderDetailDTO;
import com.classicmodels.order.dto.OrderDetailRegistrationRequest;
import com.classicmodels.order.entities.Order;
import com.classicmodels.order.entities.OrderDetail;
import com.classicmodels.order.entities.Product;
import com.classicmodels.order.mapper.OrderDetailMapper;
import com.classicmodels.order.repository.OrderDetailRepository;
import com.classicmodels.order.repository.OrderRepository;
import com.classicmodels.order.repository.ProductBackupRepository;
import com.classicmodels.order.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductBackupRepository productbackuprepo;

	@Override
	public OrderDetailDTO add(OrderDetailRegistrationRequest req) {
		OrderDetail orderDetail = orderDetailMapper.mapToOrderDetail(req);
		OrderDetail saved = orderDetailRepo.save(orderDetail);
		OrderDetailDTO orderDetailDTO = orderDetailMapper.mapToOrderDetailDTO(saved);
		return orderDetailDTO;
	}

	@Override
	public List<OrderDetailDTO> getAll() {
		return orderDetailRepo.findAll()
				.stream()
				.map(e-> orderDetailMapper.mapToOrderDetailDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public OrderDetailDTO get(Long id) {
		return orderDetailRepo.findById(id)
				.map(e-> orderDetailMapper.mapToOrderDetailDTO(e))
				.orElseThrow(()-> new RuntimeException("OrderDetail with id "+id+" not found"));
	}

	@Override
	public OrderDetailDTO update(Long id, OrderDetailRegistrationRequest req) {
		OrderDetail orderDetailDB = orderDetailRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("OrderDetail with id "+id+" not found"));
		if (req.orderNumber()!= null) {
			Order orderNumber = orderRepo.findById(req.orderNumber()).orElseThrow(()-> new RuntimeException("Order with order number "+req.orderNumber()+" not found"));
			 if(orderDetailDB.getOrder() == null) {
				 orderDetailDB.setOrder(orderNumber);
			 }else if(orderDetailDB.getOrder() != null && !req.orderNumber().equals(orderDetailDB.getOrder().getOrderNumber())){
				 orderDetailDB.setOrder(orderNumber);
			 }
	        }
		if (req.productCode()!= null) {
			Product productCode = productbackuprepo.findById(req.productCode()).orElseThrow(()-> new RuntimeException("Product with product code "+req.productCode()+" not found"));
			 if(orderDetailDB.getProduct() == null) {
				 orderDetailDB.setProduct(productCode);
			 }else if(orderDetailDB.getProduct() != null && !req.productCode().equals(orderDetailDB.getProduct().getProductCode())){
				 orderDetailDB.setProduct(productCode);
			 }
	        }
		if (req.quantityOrdered()!= null && req.quantityOrdered().compareTo(orderDetailDB.getQuantityOrdered()) != 0) {
			orderDetailDB.setQuantityOrdered(req.quantityOrdered());
	        }
		if (req.priceEach()!= null && req.priceEach().compareTo(orderDetailDB.getPriceEach()) != 0) {
			orderDetailDB.setPriceEach(req.priceEach());
	        }
		if (req.orderLineNumber()!= null && req.orderLineNumber().compareTo(orderDetailDB.getOrderLineNumber()) != 0) {
			orderDetailDB.setOrderLineNumber(req.orderLineNumber());
	        }
		OrderDetail saved = orderDetailRepo.save(orderDetailDB);
		OrderDetailDTO orderDetailDTO = orderDetailMapper.mapToOrderDetailDTO(saved);
		return orderDetailDTO; 
		
	}

	@Override
	public void delete(Long id) {
		orderDetailRepo.deleteById(id);
	}

}
