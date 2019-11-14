package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "inventory_item", schema = "public", catalog = "e_com_db")
public class InventoryItem {
    private String inventoryItemId;
    private String productId;
    private Integer quantity;
    private Status inventoryStatusByStatusId;

    @OneToOne
    @JoinColumn(name = "statusid", referencedColumnName = "statusid")
    public Status getInventoryStatusByStatusId() {
        return inventoryStatusByStatusId;
    }

    public void setInventoryStatusByStatusId(Status inventoryStatusByStatusId) {
        this.inventoryStatusByStatusId = inventoryStatusByStatusId;
    }

    @Id
    @Column(name = "inventoryitemid", nullable = false, length = 20)
    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(String inventoryitemid) {
        this.inventoryItemId = inventoryitemid;
    }

    @Basic
    @Column(name = "productid", nullable = true, length = 20)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productid) {
        this.productId = productid;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return Objects.equals(inventoryItemId, that.inventoryItemId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryItemId, productId, quantity);
    }
}
