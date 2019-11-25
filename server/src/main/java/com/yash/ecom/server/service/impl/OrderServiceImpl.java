package com.yash.ecom.server.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ecom.server.constants.ApplicationConstants;
import com.yash.ecom.server.entity.Cart;
import com.yash.ecom.server.entity.CartItem;
import com.yash.ecom.server.entity.OrderRole;
import com.yash.ecom.server.entity.OrderStatus;
import com.yash.ecom.server.entity.Orders;
import com.yash.ecom.server.entity.TotalOrderAdjustments;
import com.yash.ecom.server.enums.AdjustmentTypes;
import com.yash.ecom.server.enums.RoleTypes;
import com.yash.ecom.server.exceptions.CreateOrderException;
import com.yash.ecom.server.repository.OrderAdjustmentsRepository;
import com.yash.ecom.server.repository.OrderItemRepository;
import com.yash.ecom.server.repository.OrderRepository;
import com.yash.ecom.server.repository.OrderRoleRepository;
import com.yash.ecom.server.repository.OrderStatusRepository;
import com.yash.ecom.server.repository.TotalCartAdjustmentsRepository;
import com.yash.ecom.server.service.OrderService;
import com.yash.ecom.server.utilities.OrderHelper;

@Service
public class OrderServiceImpl implements OrderService {

	private BigDecimal cartTotal;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderAdjustmentsRepository orderAdjustmentsRepository;

	@Autowired
	TotalCartAdjustmentsRepository totalCartAdjustmentsRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Autowired
	OrderRoleRepository orderRoleRepository;


	@Override
	public String createOrder(Cart cart) throws CreateOrderException {
		cartTotal = BigDecimal.ZERO;

		String orderId = orderRepository.getOrderId();

		/** Create order object.. **/

		try {
			Orders order = new Orders();
			/* pad this number with zeros */
			order.setOrderId(orderId);
			order.setOrderDate(cart.getCartDate());
			OrderStatus orderStatus = new OrderStatus(com.yash.ecom.server.enums.OrderStatus.Created.name(), orderId,
					cart.getCartDate());
			orderStatusRepository.saveAndFlush(orderStatus);
			order.setOrderStatusByOrderStatusId(orderStatus);
			Stream<CartItem> cartStream = Stream.of(cart.getCartItems());
			cartStream.parallel().forEach(item -> {
				cartTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(Double.parseDouble(item.getQuantity())));
			});
			order.setUser_id(cart.getUserId());
			order.setTotal(cartTotal);
			orderRepository.save(order);

			/** Create orderItems **/
			orderItemRepository
					.saveAll(OrderHelper.createCartItemfromOrderItem(Arrays.asList(cart.getCartItems()), orderId));

			/** Create OrderAdjustments **/
			orderAdjustmentsRepository.saveAll(OrderHelper.createTotalAdjustmentfromCartAdjustment(
					cart.getCartAdjustments(), orderAdjustmentsRepository, orderId));

			/** create OrderRole **/
			OrderRole user = new OrderRole();
			user.setOrderId(orderId);
			user.setRoleTypeId(RoleTypes.USER.name());
			user.setPartyId(cart.getUserId());

			OrderRole company = new OrderRole();
			company.setOrderId(orderId);
			company.setRoleTypeId(RoleTypes.COMPANY.name());
			company.setPartyId(ApplicationConstants.COMPANY_ID);
			orderRoleRepository.saveAll(Arrays.asList(user, company));

		} catch (Exception e) {
			throw new CreateOrderException();
		}
		return orderId;
	}

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> allOrders = orderRepository.findAll();
		Stream<Orders> ordersStream = allOrders.stream();
		ordersStream.parallel().forEach(order -> {
			populateOrder(order);
		});
		return allOrders;
	}

	@Override
	public List<Orders> getAllOrders(String userid) {
		List<Orders> allOrders = orderRepository.findByUserId(userid);
		Stream<Orders> ordersStream = allOrders.stream();
		ordersStream.parallel().forEach(order -> {
			populateOrder(order);
		});
		return allOrders;
	}

	@Override
	public List<String> getOrdersIds(String startdate, String enddate) {
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern( "MMddyyyyHHmmss" );
		ZonedDateTime startdateLocal = ZonedDateTime.parse( startdate , dateFormater.withZone(ZoneId.systemDefault()) );
		ZonedDateTime enddateLocal = ZonedDateTime.parse( startdate , dateFormater.withZone(ZoneId.systemDefault()) );
		Calendar startDateCal = Calendar.getInstance();
		Calendar endDateCal = Calendar.getInstance();
		startDateCal.set(startdateLocal.getYear(), startdateLocal.getMonthValue()-1, startdateLocal.getDayOfMonth(), startdateLocal.getHour(), startdateLocal.getMinute(), startdateLocal.getSecond());
		startDateCal.set(enddateLocal.getYear(), enddateLocal.getMonthValue()-1, enddateLocal.getDayOfMonth(), enddateLocal.getHour(), enddateLocal.getMinute(), enddateLocal.getSecond());
		return orderRepository.findByOrderDateBetweenDates(new Date(startDateCal.getTimeInMillis()),new Date(endDateCal.getTimeInMillis()));
	}

	private void populateOrder(Orders order) {

		/** fetch order line items **/
		order.setOrdeItems(orderItemRepository.findByOrderId(order.getOrderId()));

		/** fetch order adjustments **/
		TotalOrderAdjustments adjustments = new TotalOrderAdjustments();
		adjustments.setUserId(order.getOrderId());
		adjustments.setFreightAdjustment(orderAdjustmentsRepository.findByOrderIdAndAdjustmentType(order.getOrderId(),
				AdjustmentTypes.FREIGHT.name()));
		adjustments.setPromoAdjustment(orderAdjustmentsRepository.findByOrderIdAndAdjustmentType(order.getOrderId(),
				AdjustmentTypes.PROMO.name()));
		adjustments.setTaxAdjustment(orderAdjustmentsRepository.findByOrderIdAndAdjustmentType(order.getOrderId(),
				AdjustmentTypes.TAXS.name()));

		order.setAdjustMents(adjustments);
	}

}

