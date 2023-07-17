package com.warehouse.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "delivery_product_list")
public class DeliveryProductMap extends AbstractEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double quantity;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List <Product> products;

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
        return id == that.id && Double.compare(that.quantity, quantity) == 0 && Objects.equals(products, that.products) && Objects.equals(delivery, that.delivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, products, delivery);
    }

    @Override
    public String toString() {
        return "DeliveryProductMap{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", products=" + products +
                ", delivery=" + delivery +
                '}';
    }
}
