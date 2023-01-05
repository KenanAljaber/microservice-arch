package com.payment.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.dto.PaymentResponse;
import com.payment.model.Payment;
import com.payment.model.PaymentResponseStatus;
import com.payment.repository.PaymentRepository;

@Service
public class PaymentService {
	
	
	@Autowired
	private PaymentRepository repo;
	
	
	public Payment doPayment(Payment payment) {
		PaymentResponse response=new PaymentResponse();
		payment.setTransactionNumber(UUID.randomUUID().toString());
		if(randomPaymentValidator()){
			//payment success
			payment.setResultStatus(PaymentResponseStatus.PAYMENT_SUCCESS);
			
		}else{
			//payment failed
			payment.setResultStatus(PaymentResponseStatus.PAYMENT_FAILED);
		
		}
		Payment saved=repo.save(payment);
		return saved;
	} 
	
	public String getAllTransactions () {
		ObjectMapper mapper =new ObjectMapper();
		List<Payment> p=repo.findAll();
		String json="";
		try {
			json=mapper.writeValueAsString(p);
		}catch(Exception e) {
			json="nothing to show ER";
		}
		return json;
	}
	
	private boolean randomPaymentValidator() {
		return new Random().nextBoolean();
	}
	

}
