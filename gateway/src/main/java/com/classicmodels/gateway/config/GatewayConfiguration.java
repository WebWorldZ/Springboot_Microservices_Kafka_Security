package com.classicmodels.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfiguration {
	
	@Autowired
	private GatewayAuthenticationFilter gatewayFilter;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		 return builder.routes()
				 .route(p -> p.path("/auth/**")
						 .uri("lb://security"))
				 .route(p -> p.path("/test/**")
						 .uri("lb://security"))
				 .route(p -> p.path("/employees/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://employee"))
				 .route(p -> p.path("/customers/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://customer"))
				 .route(p -> p.path("/payments/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://customer"))
				 .route(p -> p.path("/orders/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://order"))
				 .route(p -> p.path("/orderdetails/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://order"))
				 .route(p -> p.path("/products/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://product"))
				 .route(p -> p.path("/productlines/**")
						 .filters(f -> f.filter(gatewayFilter))
						 .uri("lb://product"))
				 .build();
	}
}
