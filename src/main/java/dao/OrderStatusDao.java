package dao;

import entity.OrderProductList;
import entity.OrderStatus;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class OrderStatusDao extends MyEntityDao {

    public List<OrderStatus> getList() {
        List<OrderStatus> orderStatusList;
        Session session = getSessionFactory().openSession();
        orderStatusList = session.createQuery("FROM OrderStatus ").list();
        session.close();
        return orderStatusList;
    }

    public OrderStatus getById(int id) {
        Session session = getSessionFactory().openSession();
        OrderStatus orderStatus = session.get(OrderStatus.class, id);
        session.close();
        return orderStatus;
    }
}
