package com.classicmodels.order.dto;

import java.math.BigDecimal;

public record OrderDetailRegistrationRequest(
		Long orderNumber,
		String productCode,
		Integer quantityOrdered,
		BigDecimal priceEach,
		Integer orderLineNumber
		) {

}
