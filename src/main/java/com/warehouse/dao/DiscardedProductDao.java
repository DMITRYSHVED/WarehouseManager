package com.warehouse.dao;

import com.warehouse.entity.DiscardedProduct;
import com.warehouse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class DiscardedProductDao extends AbstractEntityDao {

    public List<DiscardedProduct> getList() {
        List<DiscardedProduct> discardedProductList;
        Session session = getSessionFactory().openSession();
        discardedProductList = session.createQuery("FROM DiscardedProduct ").list();
        session.close();
        return discardedProductList;
    }

    public DiscardedProduct getByCode(int code) {
        Session session = getSessionFactory().openSession();
        String queryString = "FROM DiscardedProduct WHERE product.code = :code";
        Query<DiscardedProduct> query = session.createQuery(queryString, DiscardedProduct.class);
        query.setParameter("code", code);
        DiscardedProduct discardedProduct = query.uniqueResult();
        session.close();
        return discardedProduct;
    }
}
