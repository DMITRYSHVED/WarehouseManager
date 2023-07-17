package com.warehouse.dao;

import com.warehouse.entity.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class RoleDao extends AbstractEntityDao {

    public List<Role> getList() {
        List<Role> roleList;
        Session session = getSessionFactory().openSession();
        roleList = session.createQuery("FROM Role ").list();
        session.close();
        return roleList;
    }
}
