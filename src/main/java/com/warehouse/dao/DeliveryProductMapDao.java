package com.warehouse.dao;

import com.warehouse.entity.DeliveryProductMap;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class DeliveryProductMapDao extends AbstractEntityDao {

    public List<DeliveryProductMapDao> getList() {
        List<DeliveryProductMapDao> deliveryProductList;
        Session session = getSessionFactory().openSession();
        deliveryProductList = session.createQuery("FROM DeliveryProductMap ").list();
        session.close();
        return deliveryProductList;
    }
}
