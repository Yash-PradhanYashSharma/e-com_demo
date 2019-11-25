package com.yash.ecom.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.server.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}
