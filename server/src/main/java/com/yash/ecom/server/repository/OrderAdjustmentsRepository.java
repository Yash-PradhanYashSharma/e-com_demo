package com.yash.ecom.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yash.ecom.server.entity.OrderAdjustment;
import com.yash.ecom.server.entity.OrderItem;

public interface OrderAdjustmentsRepository extends JpaRepository<OrderAdjustment, Long> {
	@Query(value= "SELECT public.generateOrderAdjustmentId()" , nativeQuery = true)
	public String getOrderAdjustmentId();
	
	@Query("SELECT item FROM OrderAdjustment item where item.orderId = :orderId")
	public List<OrderAdjustment> findByOrderId(@Param("orderId") String orderId);
	
	@Query("SELECT item FROM OrderAdjustment item where item.orderId = :orderId and item.adjustmentType = :adjustmentType")
	public OrderAdjustment findByOrderIdAndAdjustmentType(@Param("orderId") String orderId ,@Param("adjustmentType") String adjustmentType );

}
