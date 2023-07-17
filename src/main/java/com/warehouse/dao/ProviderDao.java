package com.warehouse.dao;

import com.warehouse.entity.Provider;
import org.hibernate.Session;
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
