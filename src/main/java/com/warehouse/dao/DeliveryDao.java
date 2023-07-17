package com.warehouse.dao;

import com.warehouse.entity.Delivery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class DeliveryDao extends AbstractEntityDao {

    public List<Delivery> getList() {
        List<Delivery> deliveryList;
        Session session = getSessionFactory().openSession();
        deliveryList = session.createQuery("FROM Delivery ").list();
        session.close();
        return deliveryList;
    }
}
