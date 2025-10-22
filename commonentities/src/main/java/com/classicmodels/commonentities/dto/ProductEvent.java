package com.classicmodels.commonentities.dto;

import java.math.BigDecimal;

public record ProductEvent(
		String productCode,
		String productName,
		String productScale,
		String productVendor,
		String productDescription,
		Integer quantityInStock,
		BigDecimal buyPrice,
		BigDecimal MSRP,
		String type
		) {

}
