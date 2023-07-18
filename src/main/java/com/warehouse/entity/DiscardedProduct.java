package com.warehouse.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "discarded_products")
public class DiscardedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private Date date;

    @Column
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public DiscardedProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        DiscardedProduct that = (DiscardedProduct) o;
        return id == that.id && quantity == that.quantity && Objects.equals(date, that.date) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, quantity, product);
    }

    @Override
    public String toString() {
        return "DiscardedProduct{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
