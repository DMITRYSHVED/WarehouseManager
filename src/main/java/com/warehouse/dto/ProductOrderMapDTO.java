package com.warehouse.dto;

import com.warehouse.util.ValidationConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class ProductOrderMapDTO {

    @NotNull(message = "выберите продукт")
    private String productId;

    @Positive(message = ValidationConstants.POSITIVE_QUANTITY)
    private int quantity;

    public ProductOrderMapDTO() {
    }

    public int getProductId() {
        return Integer.parseInt(productId);
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        ProductOrderMapDTO that = (ProductOrderMapDTO) o;
        return productId == that.productId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "ProductOrderMapDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
