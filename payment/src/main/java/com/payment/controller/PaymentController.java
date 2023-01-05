package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.model.Payment;
import com.payment.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@PostMapping 
	public String doPayment(@RequestBody Payment payment ) {
		System.out.println("The payment is here, ive been called ====> "+payment);
		Payment resp=service.doPayment(payment);
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		try{
			json=mapper.writeValueAsString(resp);
			return json;
		}catch(Exception e){
			return json;
		}
		
	}
	
	@GetMapping("/history")
	public String getAllTransactions() {
		return service.getAllTransactions();
	}
}
