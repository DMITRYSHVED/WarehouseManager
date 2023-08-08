package com.warehouse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery_product_map")
public class DeliveryProductMap extends AbstractEntity {

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
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public DeliveryProductMap() {
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

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryProductMap that = (DeliveryProductMap) o;
        return id == that.id && quantity == that.quantity && Objects.equals(product, that.product) && Objects.equals(delivery, that.delivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product, delivery);
    }

    @Override
    public String toString() {
        return "DeliveryProductMap{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", products=" + product +
                ", delivery=" + delivery +
                '}';
    }
}
