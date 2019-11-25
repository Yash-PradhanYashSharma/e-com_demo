package com.yash.ecom.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.Orders;
import com.yash.ecom.server.exceptions.CreateOrderException;

@Service
public interface OrderService {
	public String createOrder(Cart cart) throws CreateOrderException;

	public List<Orders> getAllOrders();
	public List<Orders> getAllOrders(String userid);
	public List<String> getOrdersIds(String startdate,String enddate);
}
