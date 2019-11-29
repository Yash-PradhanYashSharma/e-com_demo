package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_adjustment", schema = "public", catalog = "e_com_db2")
public class OrderAdjustment {
	
	@Column(name = "orderadjustmentid", nullable = false, length = 20)
    private String orderAdjustmentId;
	@Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "productpromoid", nullable = true, length = 20)
    private String productPromoId;
    @Basic
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;
    @Basic
    @Column(name = "orderId", nullable = true, length = 20)
    private String orderId;
    @Basic
    @Column(name = "adjustment_type", nullable = true, length = 20)
    private String adjustmentType;
    public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    
    
    public String getOrderAdjustmentId() {
        return orderAdjustmentId;
    }

    public void setOrderAdjustmentId(String orderadjustmentid) {
        this.orderAdjustmentId = orderadjustmentid;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getProductPromoId() {
        return productPromoId;
    }

    public void setProductPromoId(String productpromoid) {
        this.productPromoId = productpromoid;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
