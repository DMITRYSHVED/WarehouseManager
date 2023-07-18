package com.warehouse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private int code;

    @Column
    private String name;

    @ManyToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && code == product.code && Objects.equals(name, product.name) && Objects.equals(productType, product.productType) && Objects.equals(storage, product.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, productType, storage);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", storage=" + storage +
                '}';
    }
}
