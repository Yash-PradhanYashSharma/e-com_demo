package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_adjustment", schema = "public", catalog = "e_com_db")
public class OrderAdjustment {
    private String orderAdjustmentId;
    private BigDecimal amount;
    private String productPromoId;

    @Id
    @Column(name = "orderadjustmentid", nullable = false, length = 20)
    public String getOrderAdjustmentId() {
        return orderAdjustmentId;
    }

    public void setOrderAdjustmentId(String orderadjustmentid) {
        this.orderAdjustmentId = orderadjustmentid;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "productpromoid", nullable = true, length = 20)
    public String getProductPromoId() {
        return productPromoId;
    }

    public void setProductPromoId(String productpromoid) {
        this.productPromoId = productpromoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAdjustment that = (OrderAdjustment) o;
        return Objects.equals(orderAdjustmentId, that.orderAdjustmentId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(productPromoId, that.productPromoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderAdjustmentId, amount, productPromoId);
    }
}
