package com.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {
	
	@RequestMapping("/orderFallBack")
	public Mono<String> orderServiceFallBack(){
		return Mono.just("Order service is taking too long to excute, please try again later.");
			
	}

	@RequestMapping("/paymentFallBack")
	public Mono<String> paymentFallBack(){
		return Mono.just("Payment service is taking too long to excute, please try again later.");
			
	}
	
	
	@RequestMapping("/footballFallBack")
	public Mono<String> footballServiceFallBack(){
		return Mono.just("Players service is taking too long to excute, please try again later.");
			
	}
	
	
}
