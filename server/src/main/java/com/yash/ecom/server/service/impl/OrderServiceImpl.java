package com.yash.ecom.server.service.impl;

import com.yash.ecom.server.constants.ApplicationConstants;
import com.yash.ecom.server.entity.OrderRole;
import com.yash.ecom.server.entity.OrderStatus;
import com.yash.ecom.server.entity.Orders;
import com.yash.ecom.server.enums.RoleTypes;
import com.yash.ecom.server.exceptions.CreateOrderException;
import com.yash.ecom.server.pojo.CartDetails;
import com.yash.ecom.server.pojo.OrderDetails;
import com.yash.ecom.server.pojo.ProductDetails;
import com.yash.ecom.server.repository.*;
import com.yash.ecom.server.service.OrderService;
import com.yash.ecom.server.utilities.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import static com.yash.ecom.server.enums.OrderStatus.*;

@Service
public class OrderServiceImpl implements OrderService {

    private BigDecimal cartTotal = BigDecimal.valueOf(0);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderAdjustmentsRepository orderAdjustmentsRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    OrderRoleRepository orderRoleRepository;

    public String createOrder(CartDetails cart) throws CreateOrderException {
        String orderId = orderRepository.getOrderId();
        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setOrderDate(cart.getCartDate());
        OrderStatus orderStatus = new OrderStatus(Created.name(), orderId, cart.getCartDate());
        orderStatusRepository.saveAndFlush(orderStatus);
        order.setOrderStatusId(orderStatus.getOrderStatusId());
        Stream<List<ProductDetails>> cartStream = Stream.of(cart.getItems());
        cartStream.parallel().forEach(productDetails ->
                productDetails.forEach(productDetail ->
                        cartTotal = cartTotal.add(productDetail.getPrice().multiply(
                                BigDecimal.valueOf(productDetail.getSelectedQuantity())))));
        order.setUser_id(cart.getUserId());
        order.setTotal(cartTotal);
        orderRepository.save(order);
        orderItemRepository
                .saveAll(OrderHelper.createCartItemFromOrderItem(cart.getItems(), orderId));
        if (cart.getAdjustments() != null) {
            orderAdjustmentsRepository.saveAll(OrderHelper.createTotalAdjustmentFromCartAdjustment(
                    cart.getAdjustments(), orderAdjustmentsRepository, orderId, cart.getUserId()));
        }

        OrderRole user = new OrderRole();
        user.setOrderId(orderId);
        user.setRoleTypeId(RoleTypes.USER.name());
        user.setPartyId(cart.getUserId());

        OrderRole company = new OrderRole();
        company.setOrderId(orderId);
        company.setRoleTypeId(RoleTypes.COMPANY.name());
        company.setPartyId(ApplicationConstants.COMPANY_ID);
        orderRoleRepository.saveAll(Arrays.asList(user, company));

        return orderId;
    }

    @Override
    public List<OrderDetails> getAllOrders() throws CreateOrderException {
        List<Orders> allOrders = orderRepository.findAll();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        allOrders.stream().parallel().forEach(order -> orderDetailsList.add(this.populateOrder(order)));
        return orderDetailsList;
    }

    @Override
    public List<OrderDetails> getAllOrders(String userId) throws CreateOrderException {
        List<Orders> allOrders = orderRepository.findByUserId(userId);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        allOrders.stream().parallel().forEach(order -> orderDetailsList.add(this.populateOrder(order)));
        return orderDetailsList;
    }

    @Override
    public OrderDetails getOrder(String orderId) throws CreateOrderException {
        return populateOrder(orderRepository.findByOrderId(orderId));
    }

    @Override
    public OrderDetails updateStatus(String orderId, String statusId, String invoiceId) throws CreateOrderException {
        Orders order = orderRepository.findByOrderId(orderId);
        if (statusId.equals(InProcess.name())) {
            OrderStatus orderStatus = new OrderStatus(InProcess.name(), orderId, new Date(Calendar.getInstance().getTime().getTime()));
            orderStatusRepository.saveAndFlush(orderStatus);
            order.setOrderStatusId(orderStatus.getOrderStatusId());
            orderRepository.saveAndFlush(order);
        } else if (statusId.equals(Completed.name())) {
            OrderStatus orderStatus = new OrderStatus(Completed.name(), orderId, new Date(Calendar.getInstance().getTime().getTime()));
            orderStatusRepository.saveAndFlush(orderStatus);
            order.setOrderStatusId(orderStatus.getOrderStatusId());
            order.setInvoiceId(invoiceId);
            orderRepository.saveAndFlush(order);
        }
        return populateOrder(order);
    }

    private OrderDetails populateOrder(Orders order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrders(order);
        orderDetails.setOrderItems(orderItemRepository.findByOrderId(order.getOrderId()));
        orderDetails.setAdjustments(orderAdjustmentsRepository.findByOrderId(order.getOrderId()));
        orderDetails.setOrderStatuses(orderStatusRepository.findByOrderId(order.getOrderId()));
        orderDetails.setRoles(orderRoleRepository.findByOrderId(order.getOrderId()));
        return orderDetails;
    }

    @Override
    public List<OrderDetails> getOrdersIds(String startDate, String endDate) throws CreateOrderException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
        ZonedDateTime startDateLocal = ZonedDateTime.parse(startDate, dateFormatter.withZone(ZoneId.systemDefault()));
        ZonedDateTime endDateLocal = ZonedDateTime.parse(endDate, dateFormatter.withZone(ZoneId.systemDefault()));
        Calendar startDateCal = Calendar.getInstance();
        Calendar endDateCal = Calendar.getInstance();
        startDateCal.set(startDateLocal.getYear(), startDateLocal.getMonthValue() - 1, startDateLocal.getDayOfMonth(), startDateLocal.getHour(), startDateLocal.getMinute(), startDateLocal.getSecond());
        startDateCal.set(endDateLocal.getYear(), endDateLocal.getMonthValue() - 1, endDateLocal.getDayOfMonth(), endDateLocal.getHour(), endDateLocal.getMinute(), endDateLocal.getSecond());
        List<Orders> allOrders = orderRepository.findByOrderDateBetweenDates(new Date(startDateCal.getTimeInMillis()), new Date(endDateCal.getTimeInMillis()));
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        allOrders.stream().parallel().forEach(order -> orderDetailsList.add(this.populateOrder(order)));
        return orderDetailsList;
    }

}

