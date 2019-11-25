package com.yash.ecom.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yash.ecom.server.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT item FROM OrderItem item where item.orderId = :orderId")
	public List<OrderItem> findByOrderId(@Param("orderId") String orderId);
}
