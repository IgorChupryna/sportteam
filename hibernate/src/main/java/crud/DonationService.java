package crud;

import conn.HibernateUtil;
import entity.Donation;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class DonationService implements MainService<Donation> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(Donation donation) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(donation);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(Donation donation) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(donation);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Donation get(Long id) throws SQLException {
        return (Donation)session.get(Donation.class, id);
    }

    @Override
    public void del(Donation donation) throws SQLException {
        session.beginTransaction();
        session.delete(donation);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete Donation where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<Donation> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Donation> donations = (List<Donation>) session.createQuery("FROM Donation s").list();
        session.getTransaction().commit();
        return donations;
    }
}
