package com.warehouse.dao;

import com.warehouse.entity.Storage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Repository
public class StorageDao extends AbstractEntityDao {

    public List<Storage> getList() {
        List<Storage> storageList;
        Session session = getSessionFactory().openSession();
        storageList = session.createQuery("FROM Storage ").list();
        session.close();
        return storageList;
    }
}
