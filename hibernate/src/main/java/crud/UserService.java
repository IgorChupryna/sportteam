package crud;

import entity.User;
import interfaces.MainService;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class UserService implements MainService<User> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public User add(User user) throws SQLException {
        User c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(user);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public User set(User user) throws SQLException {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public User get(Long id) throws SQLException {
        return em.find(User.class, id);
    }

    @Override
    public void del(User user) throws SQLException {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAll() throws SQLException {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
}
