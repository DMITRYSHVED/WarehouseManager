package dao;

import entity.DiscardedProduct;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class DiscardedProductsDao extends MyEntityDao {

    public List<DiscardedProduct> getList() {
        List<DiscardedProduct> discardedProductList;
        Session session = getSessionFactory().openSession();
        discardedProductList = session.createQuery("FROM DiscardedProduct ").list();
        session.close();
        return discardedProductList;
    }

    public DiscardedProduct getById(int id) {
        Session session = getSessionFactory().openSession();
        DiscardedProduct discardedProduct = session.get(DiscardedProduct.class, id);
        session.close();
        return discardedProduct;
    }
}
