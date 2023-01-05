package com.plyerorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plyerorder.dto.OrderRequest;
import com.plyerorder.dto.OrderResponse;
import com.plyerorder.service.OrderService;

@RestController
@RequestMapping("/api/order/")
public class OrderController {
	
	
	@Autowired
	private OrderService service;
	
	@PostMapping
	public ResponseEntity placeOrder (@RequestBody OrderRequest request) {
		System.out.println(request);
		OrderResponse response=service.placeOrder(request);
	return ResponseEntity.ok(response);
	}

}
