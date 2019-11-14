package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Orders {
    private String orderId;
    private Date orderDate;
    private BigDecimal total;
    private OrderStatus orderStatusByOrderStatusId;

    @Id
    @Column(name = "orderid", nullable = false, length = 20)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    @Basic
    @Column(name = "orderdate", nullable = true)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderdate) {
        this.orderDate = orderdate;
    }

    @Basic
    @Column(name = "total", nullable = true, precision = 2)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId) &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(total, orders.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, total);
    }

    @ManyToOne
    @JoinColumn(name = "orderstatusid", referencedColumnName = "orderstatusid")
    public OrderStatus getOrderStatusByOrderStatusId() {
        return orderStatusByOrderStatusId;
    }

    public void setOrderStatusByOrderStatusId(OrderStatus orderStatusByOrderstatusid) {
        this.orderStatusByOrderStatusId = orderStatusByOrderstatusid;
    }
}
