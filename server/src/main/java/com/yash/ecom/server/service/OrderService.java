package com.yash.ecom.server.service;

import com.yash.ecom.server.entity.Orders;
import com.yash.ecom.server.exceptions.CreateOrderException;
import com.yash.ecom.server.pojo.CartDetails;
import com.yash.ecom.server.pojo.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    String createOrder(CartDetails cart) throws CreateOrderException;

    List<OrderDetails> getAllOrders() throws CreateOrderException;;

    List<OrderDetails> getAllOrders(String userId) throws CreateOrderException;;

    OrderDetails getOrder(String orderId) throws CreateOrderException;;

    List<OrderDetails> getOrdersIds(String startDate, String endDate) throws CreateOrderException;;

    OrderDetails updateStatus(String orderId, String statusId, String invoiceId) throws CreateOrderException;;
}
