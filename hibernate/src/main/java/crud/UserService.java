package crud;

import conn.HibernateUtil;
import entity.User;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public class UserService implements MainService<User> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(User user) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(user);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(User user) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(user);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public User get(Long id) throws SQLException {
        return (User)session.get(User.class, id);
    }

    @Override
    public void del(User user) throws SQLException {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete User where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<User> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) session.createQuery("FROM User s Order BY s.name ASC").list();
        session.getTransaction().commit();
        return users;
    }
}
