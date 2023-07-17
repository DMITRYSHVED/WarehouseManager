package com.warehouse.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "delivery")
public class Delivery extends AbstractEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date date;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Delivery() {
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return id == delivery.id && quantity == delivery.quantity && Objects.equals(date, delivery.date) && Objects.equals(provider, delivery.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, quantity, provider);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", provider=" + provider +
                '}';
    }
}
