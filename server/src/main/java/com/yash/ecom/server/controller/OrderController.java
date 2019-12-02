package com.yash.ecom.server.controller;

import com.yash.ecom.server.exceptions.CreateOrderException;
import com.yash.ecom.server.pojo.CartDetails;
import com.yash.ecom.server.pojo.Order;
import com.yash.ecom.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<?> createOrder(@RequestBody CartDetails cart) throws CreateOrderException {
        String orderId = orderService.createOrder(cart);
        Map<String, String> returnEntity = new HashMap<>();
        returnEntity.put("orderId", orderId);
        returnEntity.put("message", "Order Created Successfully");
        return ResponseEntity.ok(returnEntity);
    }

    /**
     * Generic get method for getting all orders
     *
     * @return list of all order
     */
    @RequestMapping(method = RequestMethod.GET, path = "/getOrders")
    public ResponseEntity<?> getOrders(@RequestParam(name = "userId", required = false) String userId,
                                       @RequestParam(name = "startDate", required = false) String startDate,
                                       @RequestParam(name = "endDate", required = false) String endDate) throws CreateOrderException {
        if (userId != null) {
            return ResponseEntity.ok(orderService.getAllOrders(userId));
        } else if (startDate != null || endDate != null) {
            return ResponseEntity.ok(orderService.getOrdersIds(startDate, endDate));
        } else {
            return ResponseEntity.ok(orderService.getAllOrders());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getOrder")
    public ResponseEntity<?> getOrder(@RequestParam(name = "orderId") String orderId) throws CreateOrderException {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getOrderSpring")
    public ResponseEntity<?> getOrderSpring(@RequestBody Order order) throws CreateOrderException {
        return ResponseEntity.ok(orderService.getOrder(order.getOrderId()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestParam(name = "orderId") String orderId, @RequestParam(name = "statusId") String statusId, @RequestParam(name = "invoiceId") String invoiceId) throws CreateOrderException {
        return ResponseEntity.ok(orderService.updateStatus(orderId, statusId, invoiceId));
    }
}
