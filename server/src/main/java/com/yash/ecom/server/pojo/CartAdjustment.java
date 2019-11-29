package com.yash.ecom.server.pojo;

import java.math.BigDecimal;
import java.util.Objects;

public class CartAdjustment {
    private String productId;
    private String adjustmentTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartAdjustment)) return false;
        CartAdjustment that = (CartAdjustment) o;
        return getProductId().equals(that.getProductId()) &&
                getAdjustmentTypeId().equals(that.getAdjustmentTypeId()) &&
                getAmount().equals(that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getAdjustmentTypeId(), getAmount());
    }
}
