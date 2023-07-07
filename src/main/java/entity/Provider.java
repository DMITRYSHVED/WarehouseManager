package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider extends MyEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column
    private String contact;

    @Column
    private String address;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private List<Delivery> deliveries;

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

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", deliveries=" + deliveries +
                '}';
    }
}
