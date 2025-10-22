package com.classicmodels.gateway.config;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class GatewayAuthenticationFilter implements GatewayFilter{
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		List<String> publicURIs = List.of("/auth/register","/auth/authenticate");
		Predicate<ServerHttpRequest> isSecured = req -> publicURIs.stream().noneMatch(uri -> req.getURI().getPath().contains(uri));
		if(isSecured.test(request)) {
			if(!request.getHeaders().containsKey("Authorization"))
				return onException(exchange);
			String token = request.getHeaders().get("Authorization").get(0);
			if(token != null && token.startsWith("Bearer "))
				token = token.substring(7);
			Claims claim = jwtUtil.isTokenValid(token);
			if(claim == null)
				return onException(exchange);
		}
		return chain.filter(exchange);
	}
	
	private Mono<Void> onException(ServerWebExchange exchange) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		return response.setComplete();
	}

}
