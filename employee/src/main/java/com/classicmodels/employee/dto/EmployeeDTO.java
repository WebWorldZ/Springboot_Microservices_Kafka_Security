package com.classicmodels.employee.dto;

public record EmployeeDTO(
		Long employeeNumber,		
		String firstName,		
		String lastName,		
		String email,		
		String jobTitle,		
		String extension,		
		Long reportsTo, 	 		
		Integer officeCode
		) {

}
