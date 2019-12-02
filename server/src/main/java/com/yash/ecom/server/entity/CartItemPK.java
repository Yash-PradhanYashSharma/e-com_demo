package com.yash.ecom.server.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CartItemPK implements Serializable {
    private String userId;
    private String cartItemId;

    @Id
    @Column(name = "cartitemid", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Id
    @Column(name = "user_id", nullable = false, length = 100)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemPK)) return false;
        CartItemPK that = (CartItemPK) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getCartItemId(), that.getCartItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCartItemId());
    }
}
