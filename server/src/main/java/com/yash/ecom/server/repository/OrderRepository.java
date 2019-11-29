package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query(value = "SELECT public.generateOrderId()", nativeQuery = true)
    public String getOrderId();

    @Query("SELECT item FROM Orders item where item.orderId = :orderId")
    public Orders findByOrderId(@Param("orderId") String orderId);

    @Query("SELECT item FROM Orders item where item.user_id = :userid")
    public List<Orders> findByUserId(@Param("userid") String userid);

    @Query("SELECT item FROM Orders item where item.orderDate BETWEEN  :startdate AND :enddate")
    public List<Orders> findByOrderDateBetweenDates(@Param("startdate") Date startdate, @Param("enddate") Date enddate);
}
