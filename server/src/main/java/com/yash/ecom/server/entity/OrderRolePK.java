package com.yash.ecom.server.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderRolePK implements Serializable {
    private String orderId;
    private String partyId;

    @Column(name = "orderid", nullable = false, length = 20)
    @Id
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    @Column(name = "partyid", nullable = false, length = 20)
    @Id
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyid) {
        this.partyId = partyid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRolePK that = (OrderRolePK) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(partyId, that.partyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, partyId);
    }
}
