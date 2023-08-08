package com.warehouse.dao;

import com.warehouse.entity.ProductOrderMap;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class ProductOrderMapDao extends AbstractEntityDao {

    public List<ProductOrderMap> getList() {
        List<ProductOrderMap> productListList;
        Session session = getSessionFactory().openSession();
        productListList = session.createQuery("FROM ProductOrderMap ").list();
        session.close();
        return productListList;
    }
}
