package com.classicmodels.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.classicmodels.security.config.JwtService;
import com.classicmodels.security.user.AppUserRepository;
import com.classicmodels.security.user.Appuser;
import com.classicmodels.security.user.Role;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AppUserRepository appUserRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;

	@Override
	public void register(RegistrationRequest req) {
		Appuser user = new Appuser(); 
		user.setUname(req.username());
		user.setPword(encoder.encode(req.password()));
		if(req.role().equals("USER")) {
		user.setRole(Role.USER);	
		}else {
		user.setRole(Role.ADMIN);	
		}
		appUserRepo.save(user);
	}

	@Override
	public AuthenticatedResponse authenticate(AuthenticationRequest req) {
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
		if(authenticate.isAuthenticated()) {
			Appuser user = appUserRepo.findByUname(req.username()).orElseThrow(() -> new UsernameNotFoundException("User with user name "+req.username()+" not found"));
			String jstToken = jwtService.generateToken(user);
			return new AuthenticatedResponse(jstToken);
		}else {
			throw new RuntimeException("Username "+req.username()+" and password "+req.password()+" are invalid credentials");
		}
	}

}
