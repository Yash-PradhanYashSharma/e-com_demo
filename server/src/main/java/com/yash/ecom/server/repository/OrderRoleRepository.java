package com.yash.ecom.server.repository;

import com.yash.ecom.server.entity.OrderAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.server.entity.OrderRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRoleRepository extends JpaRepository<OrderRole, Long> {

    @Query("SELECT item FROM OrderRole item where item.orderId = :orderId")
    public List<OrderRole> findByOrderId(@Param("orderId") String orderId);

}
