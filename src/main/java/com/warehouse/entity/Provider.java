package com.warehouse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "provider")
public class Provider extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column
    private String contact;

    @Column
    private String address;

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
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return id == provider.id && Objects.equals(companyName, provider.companyName) && Objects.equals(contact, provider.contact) && Objects.equals(address, provider.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, contact, address);
    }
}
