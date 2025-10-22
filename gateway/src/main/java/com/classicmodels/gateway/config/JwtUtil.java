package com.classicmodels.gateway.config;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	private static String SECRET_KEY = "thiQXZ2cj+UnD3ny3GMy7MSpBNyje9Iz0Bhg/v4ZNaI="; 
	
	public Claims isTokenValid(String token) {
		try {
			Jws<Claims> jws = Jwts
			.parser()
			.verifyWith(getSignInKey())
			.build()
			.parseSignedClaims(token);
			
			return jws.getPayload();
		} catch (JwtException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private SecretKey getSignInKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
	}
}
