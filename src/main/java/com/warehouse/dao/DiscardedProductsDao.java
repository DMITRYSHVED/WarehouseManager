package com.warehouse.dao;

import com.warehouse.entity.DiscardedProduct;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class DiscardedProductsDao extends AbstractEntityDao {

    public List<DiscardedProduct> getList() {
        List<DiscardedProduct> discardedProductList;
        Session session = getSessionFactory().openSession();
        discardedProductList = session.createQuery("FROM DiscardedProduct ").list();
        session.close();
        return discardedProductList;
    }
}
