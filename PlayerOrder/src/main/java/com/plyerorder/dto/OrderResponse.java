package com.plyerorder.dto;

import com.plyerorder.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	private Order order;
	private RequestResult RESULT;


}
