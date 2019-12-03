package com.yash.ecom.server.utilities;

import com.yash.ecom.server.entity.OrderAdjustment;
import com.yash.ecom.server.entity.OrderItem;
import com.yash.ecom.server.pojo.CartAdjustment;
import com.yash.ecom.server.pojo.ProductDetails;
import com.yash.ecom.server.repository.OrderAdjustmentsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderHelper {

    public static List<OrderItem> createCartItemFromOrderItem(List<ProductDetails> cartItems, String orderId) {
        Integer itemCounter = 1;
        List<OrderItem> orderItems = new ArrayList<>();
        for (ProductDetails cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setOrderItemSeqId(itemCounter);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getSelectedQuantity());
            orderItem.setUnitPrice(cartItem.getPrice());
            orderItems.add(orderItem);
            itemCounter++;
        }
        return orderItems;
    }

    private static OrderAdjustment createOrderAdjustmentFromCartAdjustment(CartAdjustment cartAdjustment, String orderAdjustmentId, String orderId, String userId) {
        OrderAdjustment orderAdjustment = new OrderAdjustment();
        orderAdjustment.setAmount(cartAdjustment.getAmount());
        orderAdjustment.setAdjustmentType(cartAdjustment.getAdjustmentTypeId());
        orderAdjustment.setOrderAdjustmentId(orderAdjustmentId);
        orderAdjustment.setProductPromoId(cartAdjustment.getProductId());
        orderAdjustment.setUserId(userId);
        orderAdjustment.setOrderId(orderId);
        return orderAdjustment;
    }

    public static List<OrderAdjustment> createTotalAdjustmentFromCartAdjustment(List<CartAdjustment> cartAdjustments, OrderAdjustmentsRepository repository, String orderId, String userId) {
        List<OrderAdjustment> orderAdjustments = new ArrayList<>();
        cartAdjustments.forEach(cartAdjustment -> {
            Random rand = new Random();
            int num = rand.nextInt(9000) + 10;
            orderAdjustments.add(createOrderAdjustmentFromCartAdjustment(cartAdjustment, "OrderAdj" + num, orderId, userId));
        });
        return orderAdjustments;
    }

}

