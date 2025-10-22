package com.classicmodels.order.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.ProductEvent;
import com.classicmodels.order.dto.OrderDetailDTO;
import com.classicmodels.order.dto.OrderDetailRegistrationRequest;
import com.classicmodels.order.entities.Order;
import com.classicmodels.order.entities.OrderDetail;
import com.classicmodels.order.entities.Product;
import com.classicmodels.order.repository.OrderRepository;
import com.classicmodels.order.repository.ProductBackupRepository;

@Service
public class OrderDetailMapper {
	
	@Autowired
	private ProductBackupRepository productbackuprepo;
	
	@Autowired
	private OrderRepository orderrepo;
	
	public OrderDetailDTO mapToOrderDetailDTO(OrderDetail orderDetail) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
				orderDetail.getId(),
				orderDetail.getOrder().getOrderNumber(),
				orderDetail.getProduct().getProductCode(),
				orderDetail.getQuantityOrdered(),
				orderDetail.getPriceEach(),
				orderDetail.getOrderLineNumber()
				);
		return orderDetailDTO;
	}
	
	public OrderDetail mapToOrderDetail(OrderDetailRegistrationRequest req) {
		Order orderNumber = null; 
		if(req.orderNumber()!=null) {
			orderNumber = orderrepo.findById(req.orderNumber()).orElse(null);
		}
		Product productCode = null; 
		if(req.productCode()!=null) {
			productCode = productbackuprepo.findById(req.productCode()).orElse(null);
		}
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(orderNumber);
		orderDetail.setProduct(productCode);
		orderDetail.setQuantityOrdered(req.quantityOrdered());
		orderDetail.setPriceEach(req.priceEach());
		orderDetail.setOrderLineNumber(req.orderLineNumber());
		return orderDetail; 
	}

	public Product mapEventToProduct(ProductEvent event) {
		Product product = new Product(
				event.productCode(),
				event.productName(),
				event.productScale(),
				event.productVendor(),
				event.productDescription(),
				event.quantityInStock(),
				event.buyPrice(),
				event.MSRP()
				);
		return product; 
	}
}
