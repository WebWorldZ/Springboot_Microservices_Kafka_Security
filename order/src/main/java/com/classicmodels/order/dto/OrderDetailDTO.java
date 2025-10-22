package com.classicmodels.order.dto;

import java.math.BigDecimal;

public record OrderDetailDTO(
		Long id,
		Long orderNumber,
		String productCode,
		Integer quantityOrdered,
		BigDecimal priceEach,
		Integer orderLineNumber
		) {

}
