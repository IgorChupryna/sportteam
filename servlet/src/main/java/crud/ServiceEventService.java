package crud;

import entity.ServiceEvent;
import interfaces.MainService;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class ServiceEventService implements MainService<ServiceEvent> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public ServiceEvent add(ServiceEvent serviceEvent) throws SQLException {
        ServiceEvent c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(serviceEvent);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public ServiceEvent set(ServiceEvent serviceEvent) throws SQLException {
        em.getTransaction().begin();
        em.merge(serviceEvent);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public ServiceEvent get(Long id) throws SQLException {
        return em.find(ServiceEvent.class, id);
    }

    @Override
    public void del(ServiceEvent serviceEvent) throws SQLException {
        em.getTransaction().begin();
        em.remove(serviceEvent);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<ServiceEvent> getAll() throws SQLException {
        TypedQuery<ServiceEvent> namedQuery = em.createNamedQuery("ServiceEvent.getAll", ServiceEvent.class);
        return namedQuery.getResultList();
    }
}
