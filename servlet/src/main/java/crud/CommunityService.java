package crud;

import entity.Community;
import interfaces.MainService;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class CommunityService  implements MainService<Community> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public Community add(Community community) throws SQLException {
        Community c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(community);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public void set(Community community) throws SQLException {
        em.getTransaction().begin();
        em.merge(community);
        em.getTransaction().commit();
    }

    @Override
    public Community get(Long id) throws SQLException {
        return em.find(Community.class, id);
    }

    @Override
    public void del(Community community) throws SQLException {
        em.getTransaction().begin();
        em.remove(community);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<Community> getAll() throws SQLException {
        TypedQuery<Community> namedQuery = em.createNamedQuery("Community.getAll", Community.class);
        return namedQuery.getResultList();
    }
}
