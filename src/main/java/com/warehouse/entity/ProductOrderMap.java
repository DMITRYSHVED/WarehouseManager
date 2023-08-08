package com.warehouse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_product_map")
public class ProductOrderMap extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;

    public ProductOrderMap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderMap that = (ProductOrderMap) o;
        return id == that.id && quantity == that.quantity && Objects.equals(product, that.product) && Objects.equals(productOrder, that.productOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product, productOrder);
    }

    @Override
    public String toString() {
        return "ProductOrderMap{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", productOrder=" + productOrder +
                '}';
    }
}
