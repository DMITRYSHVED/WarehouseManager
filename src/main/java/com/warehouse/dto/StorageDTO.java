package com.warehouse.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class StorageDTO {

    @NotNull
    private int storageId;

    private int quantity;

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageDTO that = (StorageDTO) o;
        return storageId == that.storageId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageId, quantity);
    }

    @Override
    public String toString() {
        return "StorageDTO{" +
                "productId=" + storageId +
                ", quantity=" + quantity +
                '}';
    }
}
