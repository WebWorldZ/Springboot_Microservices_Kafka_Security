package com.classicmodels.commonentities.dto;

import java.math.BigDecimal;

public record CustomerEvent(
		Long customerNumber,
		String customerName,
		String contactLastName,
		String contactFirstName,
		String phone,
		String addressLine1,
		String addressLine2,
		String city,
		String state,
		String postalCode,
		String country,
		BigDecimal creditlimit,
		String type
		) {

}
