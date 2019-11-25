package com.yash.ecom.server.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.ecom.server.entity.OrderItem;
import com.yash.ecom.server.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	@Query(value= "SELECT public.generateOrderId()" , nativeQuery = true)
	public String getOrderId();
	
	@Query("SELECT item FROM Orders item where item.orderId = :orderId")
	public List<Orders> findByOrderId(@Param("orderId") String orderId);
	
	@Query("SELECT item FROM Orders item where item.user_id = :userid")
	public List<Orders> findByUserId(@Param("userid") String userid);
	
	@Query("SELECT item.orderId FROM Orders item where item.orderDate BETWEEN  :startdate AND :enddate")
	public List<String> findByOrderDateBetweenDates(@Param("startdate") Date startdate, @Param("enddate") Date enddate);
}
