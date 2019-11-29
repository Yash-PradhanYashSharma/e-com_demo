package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_price_type", schema = "public", catalog = "e_com_db2")
public class ProductPriceType {
    private String productPriceTypeId;
    private String description;

    @Id
    @Column(name = "productpricetypeid", nullable = false, length = 20)
    public String getProductPriceTypeId() {
        return productPriceTypeId;
    }

    public void setProductPriceTypeId(String productpricetypeid) {
        this.productPriceTypeId = productpricetypeid;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceType that = (ProductPriceType) o;
        return Objects.equals(productPriceTypeId, that.productPriceTypeId) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productPriceTypeId, description);
    }
}
