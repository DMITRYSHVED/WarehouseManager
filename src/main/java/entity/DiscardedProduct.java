package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discarded_products")
public class DiscardedProduct {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
