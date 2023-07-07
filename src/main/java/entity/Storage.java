package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "storage")
public class Storage extends MyEntity {

    @Column
    @Id
    @GeneratedValue
    private int id;

    @Column
    private double quantity;

    @OneToMany(mappedBy = "storage", fetch = FetchType.EAGER)
    private List<Product> products;

    public Storage() {
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
