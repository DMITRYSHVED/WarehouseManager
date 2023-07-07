package dao;

import entity.Provider;
import entity.Storage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class StorageDao extends MyEntityDao {

    public List<Storage> getList() {
        List<Storage> storageList;
        Session session = getSessionFactory().openSession();
        storageList = session.createQuery("FROM Storage ").list();
        session.close();
        return storageList;
    }

    public Storage getById(int id) {
        Session session = getSessionFactory().openSession();
        Storage storage = session.get(Storage.class, id);
        session.close();
        return storage;
    }
}
