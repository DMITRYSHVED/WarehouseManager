package com.warehouse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "storage")
public class Storage extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Storage() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return id == storage.id && quantity == storage.quantity && Objects.equals(product, storage.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
