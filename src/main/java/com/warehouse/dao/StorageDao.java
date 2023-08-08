package com.warehouse.dao;

import com.warehouse.entity.Product;
import com.warehouse.entity.Storage;
import com.warehouse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

    public Storage getByProductID(int productId) {
        Session session = getSessionFactory().openSession();
        String queryString = "FROM Storage WHERE product_id = :product";
        Query<Storage> query = session.createQuery(queryString, Storage.class);
        query.setParameter("product", productId);
        Storage storage = query.uniqueResult();
        session.close();
        return storage;
    }
}
