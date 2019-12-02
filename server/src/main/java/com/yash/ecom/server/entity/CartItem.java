package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cart_item", schema = "public", catalog = "e_com_db")
public class CartItem {

    private String cartItemId;
    private String productId;
    private Integer quantity;
    private Integer unitPrice;

    @Id
    @Column(name = "cartitemid", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
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
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unitprice")
    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitprice) {
        this.unitPrice = unitprice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(getCartItemId(), cartItem.getCartItemId()) &&
                Objects.equals(getProductId(), cartItem.getProductId()) &&
                Objects.equals(getQuantity(), cartItem.getQuantity()) &&
                Objects.equals(getUnitPrice(), cartItem.getUnitPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartItemId(), getProductId(), getQuantity(), getUnitPrice());
    }
}
