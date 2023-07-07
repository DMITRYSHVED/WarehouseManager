package dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class DeliveryProductList extends MyEntityDao {

    public List<DeliveryProductList> getList() {
        List<DeliveryProductList> deliveryProductList;
        Session session = getSessionFactory().openSession();
        deliveryProductList = session.createQuery("FROM DeliveryProductList ").list();
        session.close();
        return deliveryProductList;
    }

    public DeliveryProductList getById(int id) {
        Session session = getSessionFactory().openSession();
        DeliveryProductList deliveryProductList = session.get(DeliveryProductList.class, id);
        session.close();
        return deliveryProductList;
    }
}
