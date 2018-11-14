package crud;

import conn.HibernateUtil;
import entity.Community;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class CommunityService  implements MainService<Community> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(Community community) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(community);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(Community community) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(community);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Community get(Long id) throws SQLException {
        return (Community)session.get(Community.class, id);
    }

    @Override
    public void del(Community community) throws SQLException {
        session.beginTransaction();
        session.delete(community);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete Community where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<Community> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Community> communities = (List<Community>) session.createQuery("FROM Community s").list();
        session.getTransaction().commit();
        return communities;
    }
}
