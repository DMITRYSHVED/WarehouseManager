package dao;

import entity.Delivery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class DeliveryDao extends MyEntityDao {

    public List<Delivery> getList() {
        List<Delivery> deliveryList;
        Session session = getSessionFactory().openSession();
        deliveryList = session.createQuery("FROM Delivery ").list();
        session.close();
        return deliveryList;
    }

    public Delivery getById(int id) {
        Session session = getSessionFactory().openSession();
        Delivery delivery = session.get(Delivery.class, id);
        session.close();
        return delivery;
    }
}
