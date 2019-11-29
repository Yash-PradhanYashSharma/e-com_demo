package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal productWidth;
    private BigDecimal productDepth;
    private BigDecimal productHeight;
    private BigDecimal productWeight;
    private InventoryItem inventoryItemByInventoryItemId;

    @Id
    @Column(name = "productid", nullable = false, length = 20)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productid) {
        this.productId = productid;
    }

    @Basic
    @Column(name = "productname", nullable = false, length = 50)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productname) {
        this.productName = productname;
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
    @Column(name = "productwidth")
    public BigDecimal getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(BigDecimal productwidth) {
        this.productWidth = productwidth;
    }

    @Basic
    @Column(name = "productdepth")
    public BigDecimal getProductDepth() {
        return productDepth;
    }

    public void setProductDepth(BigDecimal productdepth) {
        this.productDepth = productdepth;
    }

    @Basic
    @Column(name = "productheight")
    public BigDecimal getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(BigDecimal productheight) {
        this.productHeight = productheight;
    }

    @Basic
    @Column(name = "productweight")
    public BigDecimal getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(BigDecimal productweight) {
        this.productWeight = productweight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(description, product.description) &&
                Objects.equals(productWidth, product.productWidth) &&
                Objects.equals(productDepth, product.productDepth) &&
                Objects.equals(productHeight, product.productHeight) &&
                Objects.equals(productWeight, product.productWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, description, productWidth, productDepth, productHeight, productWeight);
    }

    @ManyToOne
    @JoinColumn(name = "inventoryitemid", referencedColumnName = "inventoryitemid")
    public InventoryItem getInventoryItemByInventoryItemId() {
        return inventoryItemByInventoryItemId;
    }

    public void setInventoryItemByInventoryItemId(InventoryItem inventoryItemByInventoryitemid) {
        this.inventoryItemByInventoryItemId = inventoryItemByInventoryitemid;
    }
}
