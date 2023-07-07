package dao;

import entity.OrderProductList;
import entity.ProductOrder;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class ProductOrderDao extends MyEntityDao {

    public List<ProductOrder> getList() {
        List<ProductOrder> productOrderList;
        Session session = getSessionFactory().openSession();
        productOrderList = session.createQuery("FROM ProductOrder ").list();
        session.close();
        return productOrderList;
    }

    public ProductOrder getById(int id) {
        Session session = getSessionFactory().openSession();
        ProductOrder productOrder = session.get(ProductOrder.class, id);
        session.close();
        return productOrder;
    }
}
