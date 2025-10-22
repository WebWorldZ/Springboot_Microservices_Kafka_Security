package com.classicmodels.customer.dto;

import java.math.BigDecimal;
import java.util.Date;

public record PaymentDTO(
		Long id,
		String checkNumber,
		BigDecimal amount,
		Date paymentDate,
		Long customerNumber
		) {

}
