package com.warehouse.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_product_map")
public class OrderProductMap extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private double quantity;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;

    public OrderProductMap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
        OrderProductMap that = (OrderProductMap) o;
        return id == that.id && Double.compare(that.quantity, quantity) == 0 && Objects.equals(products, that.products) && Objects.equals(productOrder, that.productOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, products, productOrder);
    }

    @Override
    public String toString() {
        return "OrderProductMap{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", products=" + products +
                ", productOrder=" + productOrder +
                '}';
    }
}
