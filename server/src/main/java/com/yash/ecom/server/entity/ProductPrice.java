package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "product_price", schema = "public", catalog = "e_com_db")
@IdClass(ProductPricePK.class)
public class ProductPrice {
    private String productId;
    private String productPriceTypeId;
    private String description;
    private Date fromDate;
    private Date thruDate;
    private Integer price;
    private Product productByProductId;

    @Id
    @Column(name = "productid", nullable = false, length = 20)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productid) {
        this.productId = productid;
    }

    @Id
    @Column(name = "productPriceTypeId", nullable = false, length = 20)
    public String getProductPriceTypeId() {
        return productPriceTypeId;
    }

    public void setProductPriceTypeId(String productPriceTypeId) {
        this.productPriceTypeId = productPriceTypeId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "fromdate", nullable = true)
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromdate) {
        this.fromDate = fromdate;
    }

    @Basic
    @Column(name = "thrudate", nullable = true)
    public Date getThruDate() {
        return thruDate;
    }

    public void setThruDate(Date thrudate) {
        this.thruDate = thrudate;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(productPriceTypeId, that.productPriceTypeId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(thruDate, that.thruDate) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productPriceTypeId, description, fromDate, thruDate, price);
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", insertable = false, updatable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductid) {
        this.productByProductId = productByProductid;
    }
}
