package crud;

import entity.Donation;
import interfaces.MainService;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class DonationService implements MainService<Donation> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public Donation add(Donation donation) throws SQLException {
        Donation c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(donation);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public void set(Donation donation) throws SQLException {
        em.getTransaction().begin();
        em.merge(donation);
        em.getTransaction().commit();
    }

    @Override
    public Donation get(Long id) throws SQLException {
        return em.find(Donation.class, id);
    }

    @Override
    public void del(Donation donation) throws SQLException {
        em.getTransaction().begin();
        em.remove(donation);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<Donation> getAll() throws SQLException {
        TypedQuery<Donation> namedQuery = em.createNamedQuery("Donation.getAll", Donation.class);
        return namedQuery.getResultList();
    }
}
