package dao;

import entity.Provider;
import entity.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class RoleDao extends MyEntityDao {

    public List<Role> getList() {
        List<Role> roleList;
        Session session = getSessionFactory().openSession();
        roleList = session.createQuery("FROM Role ").list();
        session.close();
        return roleList;
    }

    public Role getById(int id) {
        Session session = getSessionFactory().openSession();
        Role role = session.get(Role.class, id);
        session.close();
        return role;
    }
}
