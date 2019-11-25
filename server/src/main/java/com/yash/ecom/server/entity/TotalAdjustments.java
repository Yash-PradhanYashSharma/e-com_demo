package com.yash.ecom.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "total_cart_adjustments", schema = "public", catalog = "e_com_db")
public class TotalAdjustments {
	
	@Id
	@Column(name = "userId")
    public String userId;
	
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
	private CartAdjustments freightAdjustment;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
	private CartAdjustments taxAdjustment;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
	private CartAdjustments promoAdjustment;

    
	public CartAdjustments getFreightAdjustment() {
		return freightAdjustment;
	}

	public void setFreightAdjustment(CartAdjustments freightAdjustment) {
		this.freightAdjustment = freightAdjustment;
	}

    
	public CartAdjustments getTaxAdjustment() {
		return taxAdjustment;
	}

	public void setTaxAdjustment(CartAdjustments taxAdjustment) {
		this.taxAdjustment = taxAdjustment;
	}

	public CartAdjustments getPromoAdjustment() {
		return promoAdjustment;
	}
	
	public void setPromoAdjustment(CartAdjustments promoAdjustment) {
		this.promoAdjustment = promoAdjustment;
	}

	public BigDecimal getTotalAdjusment() {
		return this.freightAdjustment.getAmount().add(this.taxAdjustment.getAmount()).add(this.promoAdjustment.getAmount());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
