package com.classicmodels.customer.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.EmployeeEvent;
import com.classicmodels.customer.entities.Employee;
import com.classicmodels.customer.mapper.CustomerMapper;
import com.classicmodels.customer.repository.EmployeeBackupRepository;

@Service
public class EmployeeConsumer {
	
	@Autowired
	private EmployeeBackupRepository employeeBackupRepo;
	
	@Autowired
	private CustomerMapper customerMapper;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);
	
//	https://docs.spring.io/spring-kafka/reference/kafka/headers.html
	@KafkaListener(topics="${spring.kafka.topic.name.consumer}",groupId="${spring.kafka.consumer.group-id}" )
	public void consume(EmployeeEvent event,@Headers Map<String, Object> allHeaders) {
		logger.info(String.format("Employee event receved in Customer service %s", event.toString()));
		saveUpdateOrDeleteEmployee(event);
	}
	
	public void saveUpdateOrDeleteEmployee(EmployeeEvent event) {
		Employee employee = customerMapper.mapEventToEmployee(event);
		try {
			if((event.type().equals("CREATE")) || (event.type().equals("UPDATE"))) {
				employeeBackupRepo.save(employee);
			}else if(event.type().equals("DELETE")) {
				employeeBackupRepo.deleteById(employee.getEmployeeNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 