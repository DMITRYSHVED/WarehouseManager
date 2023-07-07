package dao;

import entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class UserDao extends MyEntityDao {

    public List<User> getList() {
        List<User> userList;
        Session session = getSessionFactory().openSession();
        userList = session.createQuery("FROM User ").list();
        session.close();
        return userList;
    }

    public User getById(int id) {
        Session session = getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }
}
