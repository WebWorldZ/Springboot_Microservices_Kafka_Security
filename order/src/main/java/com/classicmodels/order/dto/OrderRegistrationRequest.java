package com.classicmodels.order.dto;

import java.util.Date;

public record OrderRegistrationRequest(
		Date orderDate,
		Date requiredDate,
		Date shippedDate,
		String status,
		String comments,
		Long customerNumber
		) {

}
