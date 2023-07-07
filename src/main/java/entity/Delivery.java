package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
public class Delivery extends MyEntity {

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

    @OneToOne
    @JoinColumn(name = "delivery_product_list_id")
    private DeliveryProductList deliveryProductList;

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

    public DeliveryProductList getDeliveryProductList() {
        return deliveryProductList;
    }

    public void setDeliveryProductList(DeliveryProductList deliveryProductList) {
        this.deliveryProductList = deliveryProductList;
    }
}
