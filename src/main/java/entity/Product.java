package entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product extends MyEntity {

    @Column
    @Id
    @GeneratedValue
    private int id;

    @Column
    private int code;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    private Storage storage;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
