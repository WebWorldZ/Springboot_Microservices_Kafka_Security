package com.classicmodels.customer.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.customer.dto.PaymentDTO;
import com.classicmodels.customer.dto.PaymentRegistrationRequest;
import com.classicmodels.customer.entities.Customer;
import com.classicmodels.customer.entities.Payment;
import com.classicmodels.customer.mapper.PaymentMapper;
import com.classicmodels.customer.repository.CustomerRepository;
import com.classicmodels.customer.repository.PaymentRepository;
import com.classicmodels.customer.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepo; 
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public PaymentDTO add(PaymentRegistrationRequest req) {
		Payment payment = paymentMapper.mapToPayment(req);
		Payment saved = paymentRepo.save(payment);
		PaymentDTO paymentDTO = paymentMapper.mapToPaymentDTO(saved);
		return paymentDTO;
	}

	@Override
	public List<PaymentDTO> getAll() {
		return paymentRepo.findAll()
		.stream()
		.map(e-> paymentMapper.mapToPaymentDTO(e))
		.collect(Collectors.toList());
	}

	@Override
	public PaymentDTO get(Long id) {
		return paymentRepo.findById(id)
				.map(e-> paymentMapper.mapToPaymentDTO(e))
				.orElseThrow(()-> new RuntimeException("Payment with id "+id+" not found"));
	}

	@Override
	public PaymentDTO update(Long id, PaymentRegistrationRequest req) {
		Payment paymentDB = paymentRepo.findById(id).orElseThrow(()-> new RuntimeException("Payment with id "+id+" not found"));
		if (req.checkNumber()!= null && !req.checkNumber().equals(paymentDB.getCheckNumber())) {
			paymentDB.setCheckNumber(req.checkNumber());
	        }
		if (req.amount()!= null && req.amount().compareTo(paymentDB.getAmount()) != 0) {
			paymentDB.setAmount(req.amount());
	        }
		if (req.paymentDate()!= null && req.paymentDate().compareTo(paymentDB.getPaymentDate()) != 0) {
			paymentDB.setPaymentDate(req.paymentDate());
	        }
		if (req.customerNumber()!= null) {
			Customer customerNumber = customerRepo.findById(req.customerNumber()).orElseThrow(()-> new RuntimeException("Customer with id "+req.customerNumber()+" not found"));
			 if(paymentDB.getCustomer() == null) {
				 paymentDB.setCustomer(customerNumber);
			 }else if(paymentDB.getCustomer() != null && !req.customerNumber().equals(paymentDB.getCustomer().getCustomerNumber())){
				 paymentDB.setCustomer(customerNumber);
			 }
	        }
		Payment saved = paymentRepo.save(paymentDB);
		PaymentDTO paymentDTO = paymentMapper.mapToPaymentDTO(saved);
		return paymentDTO; 
	}

	@Override
	public void delete(Long id) {
		paymentRepo.deleteById(id);
	}

}
