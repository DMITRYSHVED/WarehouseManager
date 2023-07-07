package dao;

import entity.OrderProductList;
import entity.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class ProductDao extends MyEntityDao {

    public List<Product> getList() {
        List<Product> productList;
        Session session = getSessionFactory().openSession();
        productList = session.createQuery("FROM Product ").list();
        session.close();
        return productList;
    }

    public Product getById(int id) {
        Session session = getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }
}
