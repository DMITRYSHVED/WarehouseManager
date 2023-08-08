package com.warehouse.dto;

import com.warehouse.util.ValidationConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ProductDTO {
    private int id;

    private int code;

    @NotBlank(message = "укажите название товара")
    private String name;

    private int ProductType;

    @NotBlank
    @Size(max = 250,message = "Размер описания доллжен быть в диапазоне от 0 до 250")
    private String description;

    public ProductDTO() {
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

    public int getProductType() {
        return ProductType;
    }

    public void setProductType(int productType) {
        ProductType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", ProductType=" + ProductType +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return code == that.code && ProductType == that.ProductType && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, ProductType, description);
    }
}
