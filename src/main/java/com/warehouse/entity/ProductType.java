package com.warehouse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_type")
public class ProductType extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String category;

    @Column(name = "category_description")
    private String categoryDescription;

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

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String description) {
        this.categoryDescription = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return id == that.id && Objects.equals(category, that.category) && Objects.equals(categoryDescription, that.categoryDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, categoryDescription);
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
