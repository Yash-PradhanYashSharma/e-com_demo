package com.yash.ecom.server.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item", schema = "public", catalog = "e_com_db")
public class CartItem {

    private String userId;
    private String productId;
    private String quantity;
    private BigDecimal unitPrice;

    @Id
    @Column(name = "userId", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
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
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(getUserId(), cartItem.getUserId()) &&
                Objects.equals(getProductId(), cartItem.getProductId()) &&
                Objects.equals(getQuantity(), cartItem.getQuantity()) &&
                Objects.equals(getUnitPrice(), cartItem.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getProductId(), getQuantity(), getUnitPrice());
    }
}
