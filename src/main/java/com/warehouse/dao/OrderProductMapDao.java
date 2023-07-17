package com.warehouse.dao;

import com.warehouse.entity.OrderProductMap;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class OrderProductMapDao extends AbstractEntityDao {

    public List<OrderProductMap> getList() {
        List<OrderProductMap> productListList;
        Session session = getSessionFactory().openSession();
        productListList = session.createQuery("FROM OrderProductMap ").list();
        session.close();
        return productListList;
    }
}
