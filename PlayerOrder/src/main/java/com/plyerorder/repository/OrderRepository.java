package com.plyerorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plyerorder.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
