package dao;

import entity.MyEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import static config.FactoryManager.getSessionFactory;

@Repository
public abstract class MyEntityDao {

    public void add(MyEntity myEntity) {
        Session session = null;
        Transaction writeTransaction = null;

        try {
            session = getSessionFactory().openSession();
            writeTransaction = session.beginTransaction();
            session.save(myEntity);
        } catch (HibernateException exception) {
            //log
        } finally {
            try {
                if (writeTransaction != null) {
                    writeTransaction.commit();
                }
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException exception) {
                //log
            }
        }
    }

    public void update(MyEntity myEntity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(myEntity);
        } catch (HibernateException exception) {
            //log
        } finally {
            try {
                if (transaction != null) {
                    transaction.commit();
                }
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException exception) {
                //log
            }
        }
    }

    public void delete(MyEntity myEntity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(myEntity);
        } catch (HibernateException exception) {
            //log
        } finally {
            try {
                if (transaction != null) {
                    transaction.commit();
                }
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException exception) {
                //log
            }
        }
    }
}
