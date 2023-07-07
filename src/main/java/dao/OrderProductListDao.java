package dao;

import entity.Delivery;
import entity.MyEntity;
import entity.OrderProductList;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class OrderProductListDao extends MyEntityDao {

    public List<OrderProductList> getList() {
        List<OrderProductList> productListList;
        Session session = getSessionFactory().openSession();
        productListList = session.createQuery("FROM OrderProductList ").list();
        session.close();
        return productListList;
    }

    public OrderProductList getById(int id) {
        Session session = getSessionFactory().openSession();
        OrderProductList productList = session.get(OrderProductList.class, id);
        session.close();
        return productList;
    }
}
