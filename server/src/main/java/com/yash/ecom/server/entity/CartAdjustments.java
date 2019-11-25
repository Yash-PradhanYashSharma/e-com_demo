package com.yash.ecom.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart_adjustments", schema = "public", catalog = "e_com_db")
public class CartAdjustments{
	
    @Column(name = "userId", nullable = false, length = 20)
    private String userId;
	
    @Id
    @Column(name = "productId", nullable = true, length = 20)
	private String productId;
    
    @Column(name = "adjustmentTypeId", nullable = false, length = 20)
	private String adjustmentTypeId;
    
    @Column(name = "amount", nullable = true)
	private BigDecimal amount;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAdjustmentTypeId() {
		return adjustmentTypeId;
	}
	public void setAdjustmentTypeId(String adjustmentTypeId) {
		this.adjustmentTypeId = adjustmentTypeId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
