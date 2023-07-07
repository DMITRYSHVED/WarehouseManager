package entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends MyEntity {

    @Column
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @OneToOne(mappedBy = "role")
    private User user;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
