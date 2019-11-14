package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "order_item", schema = "public", catalog = "e_com_db")
@IdClass(OrderItemPK.class)
public class OrderItem {
    private String orderId;
    private BigInteger orderItemSeqId;
    private String productId;
    private String quantity;
    private BigDecimal unitPrice;

    @Id
    @Column(name = "orderid", nullable = false, length = 20)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    @Id
    @Column(name = "orderitemseqid", nullable = false, precision = 0)
    public BigInteger getOrderItemSeqId() {
        return orderItemSeqId;
    }

    public void setOrderItemSeqId(BigInteger orderitemseqid) {
        this.orderItemSeqId = orderitemseqid;
    }

    @Basic
    @Column(name = "productid", nullable = false, length = 20)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productid) {
        this.productId = productid;
    }

    @Basic
    @Column(name = "quantity", nullable = false, length = 20)
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unitprice", nullable = true, precision = 2)
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitprice) {
        this.unitPrice = unitprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(orderId, orderItem.orderId) &&
                Objects.equals(orderItemSeqId, orderItem.orderItemSeqId) &&
                Objects.equals(productId, orderItem.productId) &&
                Objects.equals(quantity, orderItem.quantity) &&
                Objects.equals(unitPrice, orderItem.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderItemSeqId, productId, quantity, unitPrice);
    }
}
