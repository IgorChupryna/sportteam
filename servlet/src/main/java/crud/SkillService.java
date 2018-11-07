package crud;

import entity.Skill;
import interfaces.MainService;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class SkillService implements MainService<Skill> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public Skill add(Skill skill) throws SQLException {
        Skill c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(skill);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public void set(Skill skill) throws SQLException {
        em.getTransaction().begin();
        em.merge(skill);
        em.getTransaction().commit();
    }

    @Override
    public Skill get(Long id) throws SQLException {
        return em.find(Skill.class, id);
    }

    @Override
    public void del(Skill skill) throws SQLException {
        em.getTransaction().begin();
        em.remove(skill);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        TypedQuery<Skill> namedQuery = em.createNamedQuery("Skill.getAll", Skill.class);
        return namedQuery.getResultList();
    }
}
