package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cart", schema = "public", catalog = "e_com_db")
public class Cart {

	@Id
    @Column(name = "userId", nullable = false, length = 100)
    private String userId;
	
	@Basic
    @Column(name = "cartdate", nullable = true)
    private Date cartDate;
	
    @OneToMany
    @OrderColumn
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @Column(name = "cartItems", nullable = true)
    private CartItem[] cartItems;
    
    @OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
    private TotalAdjustments cartAdjustments;

    
    public CartItem[] getCartItems() {
        return cartItems;
    }
    public void setCartItems(CartItem[] cartItems) {
        this.cartItems = cartItems;
    }
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Date getCartDate() {
        return cartDate;
    }
    public void setCartDate(Date cartDate) {
        this.cartDate = cartDate;
    }

    
    public TotalAdjustments getCartAdjustments() {
		return cartAdjustments;
	}
    
    
	public void setCartAdjustments(TotalAdjustments cartAdjustments) {
		this.cartAdjustments = cartAdjustments;
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
