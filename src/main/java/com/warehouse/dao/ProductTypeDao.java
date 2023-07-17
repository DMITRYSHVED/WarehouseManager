package com.warehouse.dao;

import com.warehouse.entity.ProductType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class ProductTypeDao extends AbstractEntityDao {

    public List<ProductType> getList() {
        List<ProductType> productTypeList;
        Session session = getSessionFactory().openSession();
        productTypeList = session.createQuery("FROM ProductType ").list();
        session.close();
        return productTypeList;
    }
}
