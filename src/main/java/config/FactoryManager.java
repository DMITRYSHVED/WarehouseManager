package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FactoryManager {

    private static volatile FactoryManager instance;
    private static SessionFactory sessionFactory;

    private FactoryManager() {
    }

    public static SessionFactory getSessionFactory() {

        if (instance != null) {
            return sessionFactory;
        }
        synchronized (FactoryManager.class) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Provider.class);
            configuration.addAnnotatedClass(Delivery.class);
            configuration.addAnnotatedClass(DeliveryProductList.class);
            configuration.addAnnotatedClass(OrderProductList.class);
            configuration.addAnnotatedClass(OrderStatus.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(ProductOrder.class);
            configuration.addAnnotatedClass(ProductType.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Storage.class);
            configuration.addAnnotatedClass(User.class);
            configuration.configure();
            StandardServiceRegistry registryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(registryBuilder);
            instance = new FactoryManager();
        }
        return sessionFactory;
    }
}
