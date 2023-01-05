package com.plyerorder.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.plyerorder.dto.OrderRequest;
import com.plyerorder.dto.OrderResponse;
import com.plyerorder.dto.Payment;
import com.plyerorder.dto.RequestResult;
import com.plyerorder.model.Order;
import com.plyerorder.repository.OrderRepository;

@Service
public class OrderService {
	
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private RestTemplate rt;
	
	/* @Value("${microservice.football-service.endpoints.endpoint.uri}")
	    private String FOOTBALL_SERVICE_END_POINT;
	 
	 @Value("${microservice.PAYMENT-SERVICE.endpoints.endpoint.uri}")
	    private String PAYMENT_SERVICE_END_POINT;*/
	
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		OrderResponse response=new OrderResponse();
		Order order=orderRequest.getOrder();
		Payment paymnet=orderRequest.getPayment();

		//check if the player exists calling the football players microservice end point
		
		HashMap<String,Object> resp=rt.getForObject("http://GATEWAY-SERVICE/footballplayer/name/"+order.getPlayerName(), HashMap.class);
		System.out.println("calling footballer service ====> response is ==> "+resp);
		order.setPrice((Integer)resp.get("price"));
		paymnet.setAmount((Integer)resp.get("price"));

		//save the order in db 
		order.setResult(RequestResult.PENDING);
		Order saved=repo.save(order);

		paymnet.setOrderId(saved.getId());
		//if exists do payment 
		HashMap<String,Object> paymentResp=rt.postForObject("http://GATEWAY-SERVICE/api/payment  ", paymnet, HashMap.class);
		System.out.println("payment response is ===> "+paymentResp);
		if(paymentResp.get((String)"resultStatus").equals(RequestResult.PAYMENT_SUCCESS)){
			saved.setResult(RequestResult.SUCCESS);
			repo.save(saved);
			
			response.setOrder(saved);
			response.setRESULT(RequestResult.SUCCESS);
			return response;
		}else{
			saved.setResult(RequestResult.PAYMENT_FAILED);
			repo.save(saved);
			
			response.setOrder(saved);
			response.setRESULT(RequestResult.PAYMENT_FAILED);
			return response;
		}
		
		//if not return false orderResponse
		
		
		
	}
	
	
	

}
