package entity;

import javax.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductType extends MyEntity{

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String category;

    @Column
    private String unit;

    @Column
    private String description;

//    @OneToOne(mappedBy = "productType")
//    private Product product;

    public ProductType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
