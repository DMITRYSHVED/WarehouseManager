package com.warehouse.dao;

import com.warehouse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class UserDao extends AbstractEntityDao {

    public List<User> getList() {
        List<User> userList;
        Session session = getSessionFactory().openSession();
        userList = session.createQuery("FROM User ").list();
        session.close();
        return userList;
    }
    public User getByLogin(String login) {
        Session session = getSessionFactory().openSession();
        String queryString = "FROM User WHERE login = :login";
        Query<User> query = session.createQuery(queryString, User.class);
        query.setParameter("login", login);
        User user = query.uniqueResult();
        session.close();
        return user;
    }

    public User getByEmail (String email) {
        Session session = getSessionFactory().openSession();
        String queryString = "FROM User WHERE email = :email";
        Query<User> query = session.createQuery(queryString, User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();
        session.close();
        return user;
    }
}
