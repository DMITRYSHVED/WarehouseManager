package com.warehouse.dao;

import com.warehouse.entity.ProductOrder;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class ProductOrderDao extends AbstractEntityDao {

    public List<ProductOrder> getList() {
        List<ProductOrder> productOrderList;
        Session session = getSessionFactory().openSession();
        productOrderList = session.createQuery("FROM ProductOrder ").list();
        session.close();
        return productOrderList;
    }
}
