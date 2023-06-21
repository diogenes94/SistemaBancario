package br.com.unincor.sistemabancario.model.dao.hibernate;

import br.com.unincor.sistemabancario.connection.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author dioge
 * @param <ID>
 * @param <T>
 */
public abstract class GenericDao<ID, T> implements Serializable {
    
    private Class<T> aClass;
    private EntityManager entityManager;

    public GenericDao() {
        reflection();
    }
    
    protected EntityManager getEntityManager() {
        if(entityManager == null || !entityManager.isOpen()) {
            this.entityManager = HibernateUtil.getSessionFactory()
                    .createEntityManager();
        }
        return entityManager;
    }
    
    public T merge(T value) {
        T result;
        try (Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession()) {
            session.beginTransaction();
            result = session.merge(value);
            session.getTransaction().commit();
        }
        return result;
    }
    
    public List<T> buscarTudo() {
        Query query = getEntityManager().createQuery("from " + 
                aClass.getSimpleName());
        return query.getResultList();
    }
    
    public T buscarPorId(ID id) {
        return getEntityManager().find(aClass, id);
    }
    
    public void deletar(T value) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(value);
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    }
    
    private void reflection(){
        aClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
