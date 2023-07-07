package dao;

import entity.MyEntity;
import entity.Provider;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;


@Repository
public class ProviderDao extends MyEntityDao {

    public List<Provider> getList() {
        List<Provider> providerList;
        Session session = getSessionFactory().openSession();
        providerList = session.createQuery("FROM Provider ").list();
        session.close();
        return providerList;
    }

    public Provider getById(int id) {
        Session session = getSessionFactory().openSession();
        Provider provider = session.get(Provider.class, id);
        session.close();
        return provider;
    }
}
