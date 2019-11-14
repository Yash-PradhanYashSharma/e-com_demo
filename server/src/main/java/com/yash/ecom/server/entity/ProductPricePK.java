package com.yash.ecom.server.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductPricePK implements Serializable {
    private String productId;
    private String productPriceTypeId;

    @Column(name = "productid", nullable = false, length = 20)
    @Id
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "productpricetypeid", nullable = false, length = 20)
    @Id
    public String getProductPriceTypeId() {
        return productPriceTypeId;
    }

    public void setProductPriceTypeId(String productPriceTypeId) {
        this.productPriceTypeId = productPriceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPricePK that = (ProductPricePK) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(productPriceTypeId, that.productPriceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productPriceTypeId);
    }
}
