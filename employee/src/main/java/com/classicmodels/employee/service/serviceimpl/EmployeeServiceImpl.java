package com.classicmodels.employee.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.EmployeeEvent;
import com.classicmodels.employee.dto.EmployeeDTO;
import com.classicmodels.employee.dto.EmployeeRegistrationRequest;
import com.classicmodels.employee.entities.Employee;
import com.classicmodels.employee.entities.Office;
import com.classicmodels.employee.exception.ResourceNotFoundException;
import com.classicmodels.employee.kafka.EmployeeProducer;
import com.classicmodels.employee.mapper.EmployeeMapper;
import com.classicmodels.employee.repository.EmployeeRepository;
import com.classicmodels.employee.repository.OfficeRepository;
import com.classicmodels.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private OfficeRepository officeRepo;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeProducer employeeProducer; 
	
	@Override
	public EmployeeDTO add(EmployeeRegistrationRequest employee) {
		Employee mapToEmployee = employeeMapper.mapToEmployee(employee);
		Employee saved = employeeRepo.save(mapToEmployee);
		EmployeeDTO employeeDTO = employeeMapper.mapToEmployeeDTO(saved);
		EmployeeEvent event = employeeMapper.mapToEvent(employeeDTO,"CREATE");
		employeeProducer.sendEvent(event);
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getAll() {
		return employeeRepo.findAll()
				.stream()
				.map(e-> employeeMapper.mapToEmployeeDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO get(Long id) {
		return employeeRepo.findById(id)
				.map(e-> employeeMapper.mapToEmployeeDTO(e))
				.orElseThrow(()-> new ResourceNotFoundException("Employee with id "+id+" not found"));
	}

	@Override
	public void delete(Long id) {
		EmployeeDTO employeeDTO = employeeRepo.findById(id).map(e-> employeeMapper.mapToEmployeeDTO(e))
				.orElseThrow(()-> new ResourceNotFoundException("Employee with id "+id+" not found"));
		employeeRepo.deleteById(id);
		EmployeeEvent event = employeeMapper.mapToEvent(employeeDTO,"DELETE");
		employeeProducer.sendEvent(event);
	}

	@Override
	public EmployeeDTO update(Long id,EmployeeRegistrationRequest req) {
		Employee employeeDB = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee with id "+id+" not found"));
		 
		 if (req.firstName()!= null && !req.firstName().equals(employeeDB.getFirstName())) {
			 employeeDB.setFirstName(req.firstName());
	        }
		 if (req.lastName()!= null && !req.lastName().equals(employeeDB.getLastName())) {
			 employeeDB.setLastName(req.lastName());
	        }
		 if (req.email()!= null && !req.email().equals(employeeDB.getEmail())) {
			 employeeDB.setEmail(req.email());
	        }
		 if (req.jobTitle()!= null && !req.jobTitle().equals(employeeDB.getJobTitle())) {
			 employeeDB.setJobTitle(req.jobTitle());
	        }
		 if (req.extension()!= null && !req.extension().equals(employeeDB.getExtension())) {
			 employeeDB.setExtension(req.extension());
	        }
		 if (req.reportsTo()!= null) {
			 Employee reports = employeeRepo.findById(req.reportsTo()).orElseThrow(()-> new ResourceNotFoundException("Employee with id "+req.reportsTo()+" not found"));
			 if(employeeDB.getReportsTo() == null) {
				 employeeDB.setReportsTo(reports);
			 }else if(employeeDB.getReportsTo() != null && !req.reportsTo().equals(employeeDB.getReportsTo().getEmployeeNumber())){
				 employeeDB.setReportsTo(reports);
			 }
	        }
		 if (req.officeCode()!= null) {
			 Office office = officeRepo.findById(req.officeCode()).orElseThrow(()-> new ResourceNotFoundException("Office with id "+req.officeCode()+" not found"));
			 if(employeeDB.getOfficeCode() == null) {
				 employeeDB.setOfficeCode(office);
			 }else if(employeeDB.getOfficeCode() != null && !req.officeCode().equals(employeeDB.getOfficeCode().getOfficecode())){
				 employeeDB.setOfficeCode(office);
			 }
	        }
		
		Employee saved = employeeRepo.save(employeeDB);
		EmployeeDTO employeeDTO = employeeMapper.mapToEmployeeDTO(saved);
		EmployeeEvent event = employeeMapper.mapToEvent(employeeDTO,"UPDATE");
		employeeProducer.sendEvent(event);
		return employeeDTO;
	}

}
