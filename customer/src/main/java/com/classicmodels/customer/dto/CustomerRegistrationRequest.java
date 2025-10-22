package com.classicmodels.customer.dto;

import java.math.BigDecimal;

public record CustomerRegistrationRequest(
		String customerName,		
		String contactFirstName,		
		String contactLastName,		
		String phone,		
		String addressLine1,		
		String addressLine2, 	 		
		String city,
		String state,
		String postalCode,
		String country,
		BigDecimal creditlimit,
		Long salesRepEmployeeNumber
		) {

}
