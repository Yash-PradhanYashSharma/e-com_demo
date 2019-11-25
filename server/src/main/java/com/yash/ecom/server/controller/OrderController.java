package com.yash.ecom.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.Orders;
import com.yash.ecom.server.exceptions.CreateOrderException;
import com.yash.ecom.server.service.OrderService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody Cart cart) throws CreateOrderException {
		String orderId = orderService.createOrder(cart);
		Map<String, String> returnEntity = new HashMap<String, String>();
		returnEntity.put("msg", "Order Created Successfully");
		returnEntity.put("orderId", orderId);
		return new ResponseEntity(returnEntity, HttpStatus.OK);
	}

	/**
	 * Generic get method for getting all orders
	 * 
	 * @return list of all orders
	 * @throws FindOrderException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getOrders(@RequestParam(name = "userid", required = false) String userid,
			@RequestParam(name = "startdate", required = false) String startdate,
			@RequestParam(name = "enddate", required = false) String enddate) throws CreateOrderException {
		List<Orders> orderList = new ArrayList<Orders>();
		if (userid != null) {
			orderList = orderService.getAllOrders(userid);
		} 
		else if(startdate !=null || enddate !=null) {
			Map<String, List<String>> returnEntity = new HashMap<String, List<String>>();
			returnEntity.put("orderIds", orderService.getOrdersIds(startdate,enddate));
			return new ResponseEntity(returnEntity, HttpStatus.OK);
		}
		else {
			orderList = orderService.getAllOrders();
		}
		return ResponseEntity.ok(orderList);
	}
}
