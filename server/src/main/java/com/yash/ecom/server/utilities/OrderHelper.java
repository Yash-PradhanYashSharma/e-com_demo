package com.yash.ecom.server.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yash.ecom.server.constants.GeneratorPrefixes;
import com.yash.ecom.server.entity.CartAdjustments;
import com.yash.ecom.server.entity.CartItem;
import com.yash.ecom.server.entity.OrderAdjustment;
import com.yash.ecom.server.entity.OrderItem;
import com.yash.ecom.server.entity.Orders;
import com.yash.ecom.server.entity.TotalAdjustments;
import com.yash.ecom.server.repository.OrderAdjustmentsRepository;
import com.yash.ecom.server.repository.OrderRepository;

public class OrderHelper {
	
	static Integer itemCouter = 1;
	static Integer orderadjustmentid = 1;
	
	public static List<OrderItem> createCartItemfromOrderItem(List<CartItem> cartItems, String orderid)
	{
		itemCouter = 1;
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderid);
			orderItem.setOrderItemSeqId(itemCouter);
			orderItem.setProductId(cartItem.getProductId());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setUnitPrice(cartItem.getUnitPrice());
			orderItems.add(orderItem);
			itemCouter++;
		}
		return orderItems;
	}

	private static OrderAdjustment createOrderAdjustmentfromCartAdjustment(CartAdjustments cartAdjustment,String orderAdjustmentId, String orderId)
	{
		OrderAdjustment orderAdjustment = new OrderAdjustment();
		orderAdjustment.setAmount(cartAdjustment.getAmount());
		orderAdjustment.setAdjustmentType(cartAdjustment.getAdjustmentTypeId());
		orderAdjustment.setOrderAdjustmentId(orderAdjustmentId);
		orderAdjustment.setProductPromoId(cartAdjustment.getProductId());
		orderAdjustment.setUserId(cartAdjustment.getUserId());
		orderAdjustment.setOrderId(orderId);
		return orderAdjustment;
	}
	
	public static List<OrderAdjustment> createTotalAdjustmentfromCartAdjustment(TotalAdjustments totalAdjustments, OrderAdjustmentsRepository repository, String orderId)
	{
		List<OrderAdjustment> orderAdjustments = new ArrayList<OrderAdjustment>();
		orderAdjustments.add(createOrderAdjustmentfromCartAdjustment(totalAdjustments.getFreightAdjustment(),repository.getOrderAdjustmentId(),orderId));
		orderAdjustments.add(createOrderAdjustmentfromCartAdjustment(totalAdjustments.getTaxAdjustment(),repository.getOrderAdjustmentId(),orderId));
		orderAdjustments.add(createOrderAdjustmentfromCartAdjustment(totalAdjustments.getPromoAdjustment(),repository.getOrderAdjustmentId(),orderId));
		return orderAdjustments;
	}

	
}

