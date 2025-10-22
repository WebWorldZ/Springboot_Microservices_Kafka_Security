package com.classicmodels.customer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.customer.dto.PaymentDTO;
import com.classicmodels.customer.dto.PaymentRegistrationRequest;
import com.classicmodels.customer.entities.Customer;
import com.classicmodels.customer.entities.Payment;
import com.classicmodels.customer.repository.CustomerRepository;

@Service
public class PaymentMapper {

	@Autowired
	private CustomerRepository customerRepo;
	
	public PaymentDTO mapToPaymentDTO(Payment payment) {
		PaymentDTO paymentDTO = new PaymentDTO(
				payment.getId(),
				payment.getCheckNumber(),
				payment.getAmount(),
				payment.getPaymentDate(),
				payment.getCustomer().getCustomerNumber()
				);
		return paymentDTO; 
	}
	
	public Payment mapToPayment(PaymentRegistrationRequest paymentReq) {
		Customer customernumber = null; 
		if(paymentReq.customerNumber()!=null) {
			customernumber = customerRepo.findById(paymentReq.customerNumber()).orElse(null);
		}
		Payment payment = new Payment(); 
		payment.setCheckNumber(paymentReq.checkNumber());
		payment.setAmount(paymentReq.amount());
		payment.setPaymentDate(paymentReq.paymentDate());
		payment.setCustomer(customernumber);
		return payment; 
	}
}
