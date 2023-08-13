package com.warehouse.dao;

import com.warehouse.entity.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.warehouse.config.FactoryManager.getSessionFactory;

@Slf4j
public abstract class AbstractEntityDao {

    public void save(AbstractEntity myEntity) {
        Session session = null;
        Transaction writeTransaction = null;

        try {
            session = getSessionFactory().openSession();
            writeTransaction = session.beginTransaction();
            session.save(myEntity);
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
        } finally {
            try {
                if (writeTransaction != null) {
                    writeTransaction.commit();
                }
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException exception) {
                log.error(exception.getMessage());
            }
        }
    }

    public void update(AbstractEntity myEntity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(myEntity);
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
        } finally {
            try {
                if (transaction != null) {
                    transaction.commit();
                }
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException exception) {
                log.error(exception.getMessage());
            }
        }
    }

    public void delete(AbstractEntity myEntity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(myEntity);
        } catch (HibernateException exception) {
            log.error(exception.getMessage());
        } finally {
            try {
                if (transaction != null) {
                    transaction.commit();
                }
                if (session != null) {
                    session.close();
                }
            } catch (HibernateException exception) {
                log.error(exception.getMessage());
            }
        }
    }

    public <T extends AbstractEntity> T findById(int id, Class<T> entityType) {
        Session session = null;
        T entity = null;
        try {
            session = getSessionFactory().openSession();
            entity = session.get(entityType, id);

        } catch (HibernateException exception) {
            log.error(exception.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }
}
