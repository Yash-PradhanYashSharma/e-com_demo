package com.yash.ecom.server.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class OrderItemPK implements Serializable {
    private String orderId;
    private Integer orderItemSeqId;

    @Column(name = "orderid", nullable = false, length = 20)
    @Id
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    @Column(name = "orderitemseqid")
    @Id
    public Integer getOrderItemSeqId() {
        return orderItemSeqId;
    }

    public void setOrderItemSeqId(Integer orderitemseqid) {
        this.orderItemSeqId = orderitemseqid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(orderItemSeqId, that.orderItemSeqId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderItemSeqId);
    }
}
