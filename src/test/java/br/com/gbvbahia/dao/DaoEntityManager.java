/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Guilherme
 */
public class DaoEntityManager {

    private static final String PERSISTENCE_TEST_UNIT = "jdbc.hsqldb.test";

    private EntityManager manager = null;

    public EntityManager getEntityManager() {
        if (this.manager == null) {
            EntityManagerFactory factory = Persistence
                    .createEntityManagerFactory(PERSISTENCE_TEST_UNIT);
            this.manager = factory.createEntityManager();
        }
        return this.manager;
    }

    public void create(Object entity) {
        this.getEntityManager().getTransaction().begin();
        try {
            this.getEntityManager().persist(entity);
        } finally {
            this.getEntityManager().getTransaction().commit();
        }
    }

    public void update(Object entity) {
        this.getEntityManager().getTransaction().begin();
        try {
            this.getEntityManager().merge(entity);
        } finally {
            this.getEntityManager().getTransaction().commit();
        }
    }

    public void remove(Object entity) {
        this.getEntityManager().getTransaction().begin();
        try {
            this.getEntityManager().remove(this.getEntityManager().merge(entity));
            this.getEntityManager().flush();
        } finally {
            this.getEntityManager().getTransaction().commit();
        }
    }

    public Object find(Class entityClass, Serializable id) {
        try {
            return this.getEntityManager().find(entityClass, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Object> findAll(Class entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = this.getEntityManager()
                .getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return this.getEntityManager().createQuery(cq).getResultList();
    }

    public void closeEntityManager() {
        this.getEntityManager().close();
    }
}
