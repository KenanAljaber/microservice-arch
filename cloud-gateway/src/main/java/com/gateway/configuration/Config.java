package com.gateway.configuration;



import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

import com.netflix.hystrix.HystrixCircuitBreaker.HystrixCircuitBreakerImpl;

@Configuration
public class Config {
	
	
	@Bean
	public RouteLocator customRouteLocator (RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p-> p.path("/api/payment/**").filters(f-> {
					f.circuitBreaker(c-> c.setName("PAYMENT-SERVICE").setFallbackUri("forward:/paymentFallBack"));
					return f;}
				).uri("lb://PAYMENT-SERVICE"))
				.route(p->  p.path("/api/order/**").filters(f->{
					f.circuitBreaker(c-> c.setName("ORDER-SERVICE").setFallbackUri("forward:/orderFallBack"));
				return f;})
						.uri("lb://ORDER-SERVICE"))
				.route(route-> route.path("/footballplayer/**").filters(filter->{
					filter.circuitBreaker(circuitBreaker-> circuitBreaker.setName("football-service").setFallbackUri("forward:/footballFallBack"));
				return filter;}).uri("lb://football-service"))
				.build();
	}

}
