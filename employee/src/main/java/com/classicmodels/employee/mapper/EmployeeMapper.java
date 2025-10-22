package com.classicmodels.employee.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.EmployeeEvent;
import com.classicmodels.employee.dto.EmployeeDTO;
import com.classicmodels.employee.dto.EmployeeRegistrationRequest;
import com.classicmodels.employee.entities.Employee;
import com.classicmodels.employee.entities.Office;
import com.classicmodels.employee.repository.EmployeeRepository;
import com.classicmodels.employee.repository.OfficeRepository;

@Service
public class EmployeeMapper {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private OfficeRepository officeRepo;

	public EmployeeDTO mapToEmployeeDTO(Employee employee) {
		Long employeenumber =  employee.getReportsTo() != null ? employee.getReportsTo().getEmployeeNumber() : null;
		Integer officecode =  employee.getOfficeCode() != null ? employee.getOfficeCode().getOfficecode() : null;
		EmployeeDTO employeeDTO = new EmployeeDTO(
				employee.getEmployeeNumber(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getJobTitle(),
				employee.getExtension(),
				employeenumber,
				officecode
				);
		return employeeDTO;
	}
	
	public Employee mapToEmployee(EmployeeRegistrationRequest employeeReq) {
		Employee reportsTo = null;
		if(employeeReq.reportsTo()!=null) {
			reportsTo = employeeRepo.findById(employeeReq.reportsTo()).orElse(null);
		}
		Office officeCode = null; 
		if(employeeReq.officeCode()!=null) {
			officeCode = officeRepo.findById(employeeReq.officeCode()).orElse(null);
		}
			
		Employee employee = new Employee();
		employee.setFirstName(employeeReq.firstName());
		employee.setLastName(employeeReq.lastName());
		employee.setEmail(employeeReq.email());
		employee.setJobTitle(employeeReq.jobTitle());
		employee.setExtension(employeeReq.extension());
		employee.setReportsTo(reportsTo);
		employee.setOfficeCode(officeCode);
		return employee;
	}
	
	public EmployeeEvent mapToEvent(EmployeeDTO dto,String type) {
		EmployeeEvent event = new EmployeeEvent(
				dto.employeeNumber(),
				dto.firstName(),
				dto.lastName(),
				dto.email(),
				dto.jobTitle(),
				dto.extension(),
				type
				);
		return event;
	}
	
}
