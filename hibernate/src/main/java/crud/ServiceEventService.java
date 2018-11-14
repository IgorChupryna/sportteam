package crud;

import conn.HibernateUtil;
import entity.ServiceEvent;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public class ServiceEventService implements MainService<ServiceEvent> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(ServiceEvent serviceEvent) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(serviceEvent);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(ServiceEvent serviceEvent) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(serviceEvent);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public ServiceEvent get(Long id) throws SQLException {
        return (ServiceEvent)session.get(ServiceEvent.class, id);
    }

    @Override
    public void del(ServiceEvent serviceEvent) throws SQLException {
        session.beginTransaction();
        session.delete(serviceEvent);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete ServiceEvent where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<ServiceEvent> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<ServiceEvent> serviceEvents = (List<ServiceEvent>) session.createQuery("FROM ServiceEvent s Order BY s.name ASC").list();
        session.getTransaction().commit();
        return serviceEvents;
    }
}
