package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_product_list")
public class DeliveryProductList extends MyEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double quantity;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List <Product> products;

    public DeliveryProductList() {
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
}
