package crud;

import entity.Comment;
import interfaces.MainService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import java.sql.SQLException;
import java.util.List;

public class CommentService implements MainService<Comment> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public Comment add(Comment comment) throws SQLException {
        Comment c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(comment);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public void set(Comment comment) throws SQLException {

    }

    @Override
    public Comment get(Long id) throws SQLException {
        return null;
    }

    @Override
    public void del(Comment comment) throws SQLException {

    }

    @Override
    public void del(Long id) throws SQLException {

    }

    @Override
    public List<Comment> getAll() throws SQLException {
        return null;
    }
}
