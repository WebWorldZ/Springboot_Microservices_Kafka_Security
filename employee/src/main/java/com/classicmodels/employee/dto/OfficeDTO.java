package com.classicmodels.employee.dto;

public record OfficeDTO(
		Integer officecode,		
		String city,		
		String phone,		
		String addressLine1,		
		String addressLine2,		
		String state,		
		String country, 		
		String postalcode, 		
		String territory) {
	
}
