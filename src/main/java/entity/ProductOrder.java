package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_order")
public class ProductOrder extends MyEntity {

    @Column
    @Id
    @GeneratedValue
    private int id;

    @Column
    private Date date;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_contact")
    private String clientContact;

    @Column(name = "client_address")
    private String clientAddress;

    @OneToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "order_product_list_id")
    private OrderProductList orderProductList;

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

    public OrderProductList getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(OrderProductList orderProductList) {
        this.orderProductList = orderProductList;
    }
}
