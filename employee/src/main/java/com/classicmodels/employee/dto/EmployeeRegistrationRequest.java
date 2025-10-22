package com.classicmodels.employee.dto;

public record EmployeeRegistrationRequest(
		String firstName,		
		String lastName,		
		String email,		
		String jobTitle,		
		String extension,		
		Long reportsTo, 	 		
		Integer officeCode
		) {

}
