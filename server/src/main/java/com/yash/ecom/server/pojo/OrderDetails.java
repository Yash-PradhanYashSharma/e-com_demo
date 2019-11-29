package com.yash.ecom.server.pojo;

import com.yash.ecom.server.entity.*;

import java.util.List;
import java.util.Objects;

public class OrderDetails {
    private Orders orders;
    private List<OrderItem> orderItems;
    private List<OrderAdjustment> adjustments;
    private List<OrderRole> roles;
    private List<OrderStatus> orderStatuses;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderAdjustment> getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(List<OrderAdjustment> adjustments) {
        this.adjustments = adjustments;
    }

    public List<OrderRole> getRoles() {
        return roles;
    }

    public void setRoles(List<OrderRole> roles) {
        this.roles = roles;
    }

    public List<OrderStatus> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(getOrders(), that.getOrders()) &&
                Objects.equals(getOrderItems(), that.getOrderItems()) &&
                Objects.equals(getAdjustments(), that.getAdjustments()) &&
                Objects.equals(getRoles(), that.getRoles()) &&
                Objects.equals(getOrderStatuses(), that.getOrderStatuses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrders(), getOrderItems(), getAdjustments(), getRoles(), getOrderStatuses());
    }
}
