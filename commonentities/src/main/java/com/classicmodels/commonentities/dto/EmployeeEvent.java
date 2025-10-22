package com.classicmodels.commonentities.dto;

public record EmployeeEvent(
		Long employeeNumber,		
		String firstName,		
		String lastName,		
		String email,		
		String jobTitle,		
		String extension,
		String type) {

}
