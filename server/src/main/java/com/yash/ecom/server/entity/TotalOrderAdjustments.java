package com.yash.ecom.server.entity;

public class TotalOrderAdjustments {
	
	public String userId;
	private OrderAdjustment freightAdjustment;
	private OrderAdjustment taxAdjustment;
	private OrderAdjustment promoAdjustment;
	
	public OrderAdjustment getFreightAdjustment() {
		return freightAdjustment;
	}
	public void setFreightAdjustment(OrderAdjustment freightAdjustment) {
		this.freightAdjustment = freightAdjustment;
	}
	public OrderAdjustment getTaxAdjustment() {
		return taxAdjustment;
	}
	public void setTaxAdjustment(OrderAdjustment taxAdjustment) {
		this.taxAdjustment = taxAdjustment;
	}
	public OrderAdjustment getPromoAdjustment() {
		return promoAdjustment;
	}
	public void setPromoAdjustment(OrderAdjustment promoAdjustment) {
		this.promoAdjustment = promoAdjustment;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
