package com.yash.ecom.server.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class CartDetails {

    private String userId;
    private Date cartDate;
    private BigDecimal itemTotal;
    private BigDecimal discountTotal;
    private BigDecimal grandTotal;
    private List<ProductDetails> items;
    private List<CartAdjustment> adjustments;

    public List<ProductDetails> getItems() {
        return items;
    }

    public void setItems(List<ProductDetails> items) {
        this.items = items;
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

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartAdjustment> getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(List<CartAdjustment> adjustments) {
        this.adjustments = adjustments;
    }

}


