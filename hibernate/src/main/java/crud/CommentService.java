package crud;

import conn.HibernateUtil;
import entity.Comment;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public class CommentService implements MainService<Comment> {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(Comment comment) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(comment);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(Comment comment) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(comment);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Comment get(Long id) throws SQLException {
        return (Comment)session.get(Comment.class, id);
    }

    @Override
    public void del(Comment comment) throws SQLException {
        session.beginTransaction();
        session.delete(comment);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete Comment where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Comment> comments = (List<Comment>) session.createQuery("FROM Comment s").list();
        session.getTransaction().commit();
        return comments;
    }
}
