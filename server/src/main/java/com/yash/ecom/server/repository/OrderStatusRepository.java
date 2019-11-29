package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    @Query("SELECT item FROM OrderStatus item where item.orderId = :orderId")
    public List<OrderStatus> findByOrderId(@Param("orderId") String orderId);

}
