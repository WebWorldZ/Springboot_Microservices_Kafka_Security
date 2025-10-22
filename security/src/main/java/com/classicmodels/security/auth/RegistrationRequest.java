package com.classicmodels.security.auth;

public record RegistrationRequest(
		String username,
		String password,
		String role
		) {

}
