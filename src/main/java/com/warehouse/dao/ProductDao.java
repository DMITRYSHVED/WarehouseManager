package com.warehouse.dao;

import com.warehouse.entity.Product;
import com.warehouse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

    public Product getByName(String name) {
        Session session = getSessionFactory().openSession();
        String queryString = "FROM Product WHERE name = :name";
        Query<Product> query = session.createQuery(queryString, Product.class);
        query.setParameter("name", name);
        Product product = query.uniqueResult();
        session.close();
        return product;
    }
}
