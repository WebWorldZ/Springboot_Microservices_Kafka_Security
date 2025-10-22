package com.classicmodels.product.dto;

import java.math.BigDecimal;

public record ProductDTO(
		String productCode, 
		String productName,
		String productLine,
		String productScale,
		String productVendor,
		String productDescription,
		Integer quantityInStock,
		BigDecimal buyPrice,
		BigDecimal MSRP
		) {

}
