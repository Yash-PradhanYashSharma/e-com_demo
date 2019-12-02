package com.yash.ecom.server.pojo;

import com.yash.ecom.server.entity.ProductPrice;
import com.yash.ecom.server.entity.ProductPromo;

import java.util.List;
import java.util.Objects;

public class ProductDetails {

    private String productId;
    private Integer quantity;
    private Integer productWidth;
    private Integer productDepth;
    private Integer productHeight;
    private Integer productWeight;
    private Integer selectedQuantity;
    private Integer price;
    private String productName;
    private String productDescription;
    private String inventoryItemStatus;
    private List<ProductPromo> productPromos;
    private List<ProductPrice> productPrices;

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

    public Integer getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Integer productWidth) {
        this.productWidth = productWidth;
    }

    public Integer getProductDepth() {
        return productDepth;
    }

    public void setProductDepth(Integer productDepth) {
        this.productDepth = productDepth;
    }

    public Integer getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Integer productHeight) {
        this.productHeight = productHeight;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
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

    public Integer getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(Integer selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
