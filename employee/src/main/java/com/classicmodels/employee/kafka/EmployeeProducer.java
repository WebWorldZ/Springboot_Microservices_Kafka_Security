package com.classicmodels.employee.kafka;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.EmployeeEvent;


@Service
public class EmployeeProducer {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeProducer.class);
	
	private NewTopic topic; 
	
	private KafkaTemplate<String, EmployeeEvent> template;

	public EmployeeProducer(NewTopic topic, KafkaTemplate<String, EmployeeEvent> template) {
		super();
		this.topic = topic;
		this.template = template;
	} 
	
	public void sendEvent(EmployeeEvent event) {
		logger.info("Employee type event "+event.toString());  
		
		Message<EmployeeEvent> message = MessageBuilder.withPayload(event)
		.setHeader(KafkaHeaders.TOPIC, topic.name())
		.build();
		
		CompletableFuture<SendResult<String, EmployeeEvent>> send = template.send(message);
		send.whenComplete((result,ex)->{
			if(ex == null) {
				System.out.println("Offset "+result.getRecordMetadata().offset()+" "
						+ "- Partition"+result.getRecordMetadata().partition());
			}else {
				System.out.println(ex.getMessage());	
			}
		});
	}
}
