package com.classicmodels.security.auth;

public interface AuthenticationService {

	void register(RegistrationRequest req);
	
	AuthenticatedResponse authenticate(AuthenticationRequest req);
}
