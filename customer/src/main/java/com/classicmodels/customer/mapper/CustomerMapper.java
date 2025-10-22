package com.classicmodels.customer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.CustomerEvent;
import com.classicmodels.commonentities.dto.EmployeeEvent;
import com.classicmodels.customer.dto.CustomerDTO;
import com.classicmodels.customer.dto.CustomerRegistrationRequest;
import com.classicmodels.customer.entities.Customer;
import com.classicmodels.customer.entities.Employee;
import com.classicmodels.customer.repository.EmployeeBackupRepository;

@Service
public class CustomerMapper {
	
	@Autowired
	private EmployeeBackupRepository employeebackuprepo; 
	
	public CustomerDTO mapToCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO(
				customer.getCustomerNumber(),
				customer.getCustomerName(),
				customer.getContactFirstName(),
				customer.getContactLastName(),
				customer.getPhone(),
				customer.getAddressLine1(),
				customer.getAddressLine2(),
				customer.getCity(),
				customer.getState(),
				customer.getPostalCode(),
				customer.getCountry(),
				customer.getCreditlimit(),
				customer.getSalesRepEmployeeNumber().getEmployeeNumber()
				);
		return customerDTO;
	}
	
	public Customer mapToCustomer(CustomerRegistrationRequest req) {
		Employee salesRepEmployeeNumber = null; 
		if(req.salesRepEmployeeNumber()!=null) {
			salesRepEmployeeNumber = employeebackuprepo.findById(req.salesRepEmployeeNumber()).orElse(null);
		}
		Customer customer = new Customer();
		customer.setCustomerName(req.customerName());
		customer.setContactFirstName(req.contactFirstName());
		customer.setContactLastName(req.contactLastName());
		customer.setPhone(req.phone());
		customer.setAddressLine1(req.addressLine1());
		customer.setAddressLine2(req.addressLine2());
		customer.setCity(req.city());
		customer.setState(req.state());
		customer.setPostalCode(req.postalCode());
		customer.setCountry(req.country());
		customer.setCreditlimit(req.creditlimit());
		customer.setSalesRepEmployeeNumber(salesRepEmployeeNumber);
		return customer;
	}
	
	public Employee mapEventToEmployee(EmployeeEvent event) {
		Employee employee = new Employee(
				event.employeeNumber(),
				event.firstName(),
				event.lastName(),
				event.email(),
				event.jobTitle(),
				event.extension()
				);
		return employee; 
	}
	
	public CustomerEvent mapToCustomerEvent(CustomerDTO dto,String type) {
		CustomerEvent event = new CustomerEvent(
				dto.customerNumber(),
				dto.customerName(),
				dto.contactLastName(),
				dto.contactFirstName(),
				dto.phone(),
				dto.addressLine1(),
				dto.addressLine2(),
				dto.city(),
				dto.state(),
				dto.postalCode(),
				dto.country(),
				dto.creditlimit(),
				type
				);
		return event;
	}

}
