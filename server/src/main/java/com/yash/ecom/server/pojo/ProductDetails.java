package com.yash.ecom.server.pojo;

import com.yash.ecom.server.entity.ProductPrice;
import com.yash.ecom.server.entity.ProductPromo;

import java.util.List;
import java.util.Objects;

public class ProductDetails {
    private String productId;
    private String productName;
    private String productDescription;
    private List<ProductPromo> productPromos;
    private List<ProductPrice> productPrices;
    private Integer quantity;
    private String inventoryItemStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInventoryItemStatus() {
        return inventoryItemStatus;
    }

    public void setInventoryItemStatus(String inventoryItemStatus) {
        this.inventoryItemStatus = inventoryItemStatus;
    }

    public List<ProductPromo> getProductPromos() {
        return productPromos;
    }

    public void setProductPromos(List<ProductPromo> productPromos) {
        this.productPromos = productPromos;
    }

    public List<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetails)) return false;
        ProductDetails details = (ProductDetails) o;
        return getProductId().equals(details.getProductId()) &&
                getProductName().equals(details.getProductName()) &&
                getProductDescription().equals(details.getProductDescription()) &&
                getProductPromos().equals(details.getProductPromos()) &&
                getProductPrices().equals(details.getProductPrices()) &&
                getQuantity().equals(details.getQuantity()) &&
                getInventoryItemStatus().equals(details.getInventoryItemStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getProductName(), getProductDescription(), getProductPromos(), getProductPrices(), getQuantity(), getInventoryItemStatus());
    }
}
