package com.classicmodels.customer.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.CustomerEvent;
import com.classicmodels.customer.dto.CustomerDTO;
import com.classicmodels.customer.dto.CustomerRegistrationRequest;
import com.classicmodels.customer.entities.Customer;
import com.classicmodels.customer.entities.Employee;
import com.classicmodels.customer.kafka.CustomerProducer;
import com.classicmodels.customer.mapper.CustomerMapper;
import com.classicmodels.customer.repository.CustomerRepository;
import com.classicmodels.customer.repository.EmployeeBackupRepository;
import com.classicmodels.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EmployeeBackupRepository employeebackuprepo;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerProducer customerProducer;

	@Override
	public CustomerDTO add(CustomerRegistrationRequest req) {
		Customer customer = customerMapper.mapToCustomer(req);
		Customer saved = customerRepo.save(customer);
		CustomerDTO customerDTO = customerMapper.mapToCustomerDTO(saved);
		CustomerEvent event = customerMapper.mapToCustomerEvent(customerDTO,"CREATE");
		customerProducer.sendEvent(event);
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getAll() {
		return customerRepo.findAll()
				.stream()
				.map(e-> customerMapper.mapToCustomerDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO get(Long id) {
		return customerRepo.findById(id)
				.map(e-> customerMapper.mapToCustomerDTO(e))
				.orElseThrow(()-> new RuntimeException("Customer with id "+id+" not found"));
	}

	@Override
	public CustomerDTO update(Long id, CustomerRegistrationRequest req) {
		Customer customerDB = customerRepo.findById(id).orElseThrow(()-> new RuntimeException("Customer with id "+id+" not found"));
		if (req.customerName()!= null && !req.customerName().equals(customerDB.getCustomerName())) {
			customerDB.setCustomerName(req.customerName());
	        }
		if (req.contactFirstName()!= null && !req.contactFirstName().equals(customerDB.getContactFirstName())) {
			customerDB.setContactFirstName(req.contactFirstName());
	        }
		if (req.contactLastName()!= null && !req.contactLastName().equals(customerDB.getContactLastName())) {
			customerDB.setContactLastName(req.contactLastName());
	        }
		if (req.phone()!= null && !req.phone().equals(customerDB.getPhone())) {
			customerDB.setPhone(req.phone());
	        }
		if (req.addressLine1()!= null && !req.addressLine1().equals(customerDB.getAddressLine1())) {
			customerDB.setAddressLine1(req.addressLine1());
	        }
		if (req.addressLine2()!= null && !req.addressLine2().equals(customerDB.getAddressLine2())) {
			customerDB.setAddressLine2(req.addressLine2());
	        }
		if (req.city()!= null && !req.city().equals(customerDB.getCity())) {
			customerDB.setCity(req.city());
	        }
		if (req.state()!= null && !req.state().equals(customerDB.getState())) {
			customerDB.setState(req.state());
	        }
		if (req.postalCode()!= null && !req.postalCode().equals(customerDB.getCountry())) {
			customerDB.setPostalCode(req.postalCode());
	        }
		if (req.country()!= null && !req.country().equals(customerDB.getCountry())) {
			customerDB.setCountry(req.country());
	        }
		if (req.creditlimit()!= null && req.creditlimit().compareTo(customerDB.getCreditlimit()) != 0) {
			customerDB.setCreditlimit(req.creditlimit());
	        }
		if (req.salesRepEmployeeNumber()!= null) {
			Employee salesRepEmployeeNumber = employeebackuprepo.findById(req.salesRepEmployeeNumber()).orElseThrow(()-> new RuntimeException("Employee with id "+req.salesRepEmployeeNumber()+" not found"));
			 if(customerDB.getSalesRepEmployeeNumber() == null) {
				 customerDB.setSalesRepEmployeeNumber(salesRepEmployeeNumber);
			 }else if(customerDB.getSalesRepEmployeeNumber() != null && !req.salesRepEmployeeNumber().equals(customerDB.getSalesRepEmployeeNumber().getEmployeeNumber())){
				 customerDB.setSalesRepEmployeeNumber(salesRepEmployeeNumber);
			 }
	        }
		Customer saved = customerRepo.save(customerDB);
		CustomerDTO customerDTO = customerMapper.mapToCustomerDTO(saved);
		CustomerEvent event = customerMapper.mapToCustomerEvent(customerDTO,"UPDATE");
		customerProducer.sendEvent(event);
		return customerDTO;
	}

	@Override
	public void delete(Long id) {
		CustomerDTO customerDTO = customerRepo.findById(id)
		.map(e-> customerMapper.mapToCustomerDTO(e))
		.orElseThrow(()-> new RuntimeException("Customer with id "+id+" not found"));
		customerRepo.deleteById(id);
		CustomerEvent event = customerMapper.mapToCustomerEvent(customerDTO,"DELETE");
		customerProducer.sendEvent(event);
	}

}
