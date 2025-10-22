package com.classicmodels.order.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.CustomerEvent;
import com.classicmodels.commonentities.dto.ProductEvent;
import com.classicmodels.order.entities.Customer;
import com.classicmodels.order.entities.Product;
import com.classicmodels.order.mapper.OrderDetailMapper;
import com.classicmodels.order.mapper.OrderMapper;
import com.classicmodels.order.repository.CustomerBackupRepository;
import com.classicmodels.order.repository.ProductBackupRepository;

@Service
public class CustomerAndProductConsumer {

	@Autowired
	private CustomerBackupRepository customerBackupRepo;
	
	@Autowired
	private ProductBackupRepository productBackupRepo;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	private static final Logger logger = LoggerFactory.getLogger(CustomerAndProductConsumer.class);
	
//	https://docs.spring.io/spring-kafka/reference/kafka/headers.html
	@KafkaListener(topics="${spring.kafka.topic.name.consumer.one}",groupId="${spring.kafka.consumer.group-id.one}" )
	public void consumeCustomer(CustomerEvent event,@Headers Map<String, Object> allHeaders) {
		logger.info(String.format("Customer event receved in Order service %s", event.toString()));
		saveUpdateOrDeleteCustomer(event);
	}
	
	public void saveUpdateOrDeleteCustomer(CustomerEvent event) {
		Customer customer = orderMapper.mapEventToCustomer(event);
		try {
			if((event.type().equals("CREATE")) || (event.type().equals("UPDATE"))) {
				customerBackupRepo.save(customer);
			}else if(event.type().equals("DELETE")) {
				customerBackupRepo.deleteById(customer.getCustomerNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@KafkaListener(topics="${spring.kafka.topic.name.consumer.two}",groupId="${spring.kafka.consumer.group-id.two}" )
	public void consumeProduct(ProductEvent event,@Headers Map<String, Object> allHeaders) {
		logger.info(String.format("Product event receved in Order service %s", event.toString()));
		saveUpdateOrDeleteProduct(event);
	}
	
	public void saveUpdateOrDeleteProduct(ProductEvent event) {
		Product product = orderDetailMapper.mapEventToProduct(event);
		try {
			if((event.type().equals("CREATE")) || (event.type().equals("UPDATE"))) {
				productBackupRepo.save(product);
			}else if(event.type().equals("DELETE")) {
				productBackupRepo.deleteById(product.getProductCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
