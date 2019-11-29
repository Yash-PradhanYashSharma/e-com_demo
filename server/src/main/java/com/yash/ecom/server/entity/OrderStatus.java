package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "order_status", schema = "public", catalog = "e_com_db2")
public class OrderStatus {
    private String orderId;
    private Date updatedDate;
    private String orderStatusId;

    public OrderStatus() {
		super();
	}

	public OrderStatus(String orderStatusId, String orderId, Date updatedDate) {
		super();
        this.orderId = orderId;
        this.updatedDate = updatedDate;
        this.orderStatusId = orderStatusId;
	}

	@Id
    @Column(name = "orderstatusid", nullable = false, length = 20)
    public String getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(String orderstatusid) {
        this.orderStatusId = orderstatusid;
    }

    @Column(name = "orderid", nullable = true, length = 20)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    @Basic
    @Column(name = "updateddate", nullable = true)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updateddate) {
        this.updatedDate = updateddate;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(orderStatusId, that.orderStatusId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatusId, orderId, updatedDate);
    }
}
