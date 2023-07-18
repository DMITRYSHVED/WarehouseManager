package com.warehouse.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "product_order")
public class ProductOrder extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private Date date;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_contact")
    private String clientContact;

    @Column(name = "client_address")
    private String clientAddress;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    public ProductOrder() {
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(clientName, that.clientName) && Objects.equals(clientContact, that.clientContact) && Objects.equals(clientAddress, that.clientAddress) && Objects.equals(orderStatus, that.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, clientName, clientContact, clientAddress, orderStatus);
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", date=" + date +
                ", clientName='" + clientName + '\'' +
                ", clientContact='" + clientContact + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
