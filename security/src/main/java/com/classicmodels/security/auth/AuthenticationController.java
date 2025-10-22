package com.classicmodels.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegistrationRequest req) {
		authService.register(req);
		return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully");
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticatedResponse> authenticate(@RequestBody AuthenticationRequest req) {
		AuthenticatedResponse response = authService.authenticate(req);
		return ResponseEntity.ok(response);
	}
}
