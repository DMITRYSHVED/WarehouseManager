package com.warehouse.dao;

import com.warehouse.entity.Product;
import com.warehouse.entity.Provider;
import com.warehouse.entity.Storage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;


@Repository
public class ProviderDao extends AbstractEntityDao {

    public List<Provider> getList() {
        List<Provider> providerList;
        Session session = getSessionFactory().openSession();
        providerList = session.createQuery("FROM Provider ").list();
        session.close();
        return providerList;
    }
}
