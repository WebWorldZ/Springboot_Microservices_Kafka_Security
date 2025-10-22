package com.classicmodels.employee.exception;

import java.time.LocalDate;

public record ErrorObject(
		String path,
		String message,
		int statusCode,
		LocalDate localDate,
		StackTraceElement[] trace
		) {

}
