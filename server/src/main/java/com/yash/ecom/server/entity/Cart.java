package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cart", schema = "public", catalog = "e_com_db")
public class Cart {

    private String userId;
    private Date cartDate;
    private CartItem[] cartItems;

    @OneToMany
    @OrderColumn
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    public CartItem[] getCartItems() {
        return cartItems;
    }
    public void setCartItems(CartItem[] cartItems) {
        this.cartItems = cartItems;
    }

    @Id
    @Column(name = "userid", nullable = false, length = 100)
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "cartdate", nullable = true)
    public Date getCartDate() {
        return cartDate;
    }
    public void setCartDate(Date cartDate) {
        this.cartDate = cartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getUserId(), cart.getUserId()) &&
                Objects.equals(getCartDate(), cart.getCartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCartDate());
    }
}
