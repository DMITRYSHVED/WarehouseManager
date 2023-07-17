package com.warehouse.dao;

import com.warehouse.entity.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class ProductDao extends AbstractEntityDao {

    public List<Product> getList() {
        List<Product> productList;
        Session session = getSessionFactory().openSession();
        productList = session.createQuery("FROM Product ").list();
        session.close();
        return productList;
    }
}
