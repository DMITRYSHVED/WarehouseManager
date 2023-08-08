package com.warehouse.entity;

import com.warehouse.util.YesNoConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "provider")
public class Provider extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @NotBlank(message = "Укажите название организации")
    @NotNull
    @Size(max = 250)
    @Column(name = "company_name")
    private String companyName;

    @NotBlank(message = "Укажите контакт")
    @NotNull
    @Size(max = 250)
    @Column
    private String contact;

    @NotBlank(message = "Укажите адресс")
    @NotNull
    @Size(max = 250)
    @Column
    private String address;

    @Column (name = "active")
    @Convert(converter = YesNoConverter.class)
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Provider() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return id == provider.id && active == provider.active && Objects.equals(companyName, provider.companyName) && Objects.equals(contact, provider.contact) && Objects.equals(address, provider.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, contact, address, active);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                '}';
    }
}
