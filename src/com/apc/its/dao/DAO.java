package com.apc.its.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Simply maps the entity manager
 * It simplifies refactoring (unitName change) and wraps some logic (limited queries)
 *
 * User: kav192
 * Date: 9/13/12
 * Time: 8:40 AM
 */
public class DAO {
    @PersistenceContext(unitName = "blog")
    private EntityManager em;

    public <E> E create(E e) {
        em.persist(e);
        return e;
    }

    public <E> E update(E e) {
        return em.merge(e);
    }

    public <E> void delete(Class<E> clazz, long id) {
        em.remove(find(clazz, id));
    }

    public <E> E find(Class<E> clazz, long id) {
        return em.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> find(String query, int min, int max) {
        return queryRange(em.createQuery(query), min, max).getResultList();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> namedFind(String query, int min, int max) {
        return queryRange(em.createNamedQuery(query), min, max).getResultList();
    }

    private static Query queryRange(Query query, int min, int max) {
        if(max >= 0) {
            query.setMaxResults(max);
        }
        if(min >= 0) {
            query.setFirstResult(min);
        }

        return query;
    }
}
