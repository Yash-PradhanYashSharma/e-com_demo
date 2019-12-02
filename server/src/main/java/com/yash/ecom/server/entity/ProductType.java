package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_type", schema = "public", catalog = "e_com_db")
public class ProductType {
    private String productTypeId;
    private String parentTypeId;
    private String childProductType;
    private String productId;

    @Id
    @Column(name = "producttypeid", nullable = false, length = 20)
    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String producttypeid) {
        this.productTypeId = producttypeid;
    }

    @Basic
    @Column(name = "parenttypeid", nullable = true, length = 20)
    public String getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(String parenttypeid) {
        this.parentTypeId = parenttypeid;
    }

    @Basic
    @Column(name = "childproducttype", nullable = true, length = 20)
    public String getChildProductType() {
        return childProductType;
    }

    public void setChildProductType(String childproducttype) {
        this.childProductType = childproducttype;
    }

    @Basic
    @Column(name = "productid", nullable = true, length = 20)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productid) {
        this.productId = productid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return Objects.equals(productTypeId, that.productTypeId) &&
                Objects.equals(parentTypeId, that.parentTypeId) &&
                Objects.equals(childProductType, that.childProductType) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTypeId, parentTypeId, childProductType, productId);
    }
}
