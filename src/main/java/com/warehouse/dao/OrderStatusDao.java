package com.warehouse.dao;

import com.warehouse.entity.OrderStatus;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class OrderStatusDao extends AbstractEntityDao {

    public List<OrderStatus> getList() {
        List<OrderStatus> orderStatusList;
        Session session = getSessionFactory().openSession();
        orderStatusList = session.createQuery("FROM OrderStatus ").list();
        session.close();
        return orderStatusList;
    }
}
