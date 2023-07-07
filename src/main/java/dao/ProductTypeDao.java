package dao;

import entity.OrderProductList;
import entity.ProductType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static config.FactoryManager.getSessionFactory;

@Repository
public class ProductTypeDao extends MyEntityDao {

    public List<ProductType> getList() {
        List<ProductType> productTypeList;
        Session session = getSessionFactory().openSession();
        productTypeList = session.createQuery("FROM ProductType ").list();
        session.close();
        return productTypeList;
    }

    public ProductType getById(int id) {
        Session session = getSessionFactory().openSession();
        ProductType productType = session.get(ProductType.class, id);
        session.close();
        return productType;
    }
}
