package com.classicmodels.security.auth;

public record AuthenticationRequest(
		String username,
		String password
		) {

}
