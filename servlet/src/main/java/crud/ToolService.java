package crud;

import entity.Tool;
import interfaces.MainService;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class ToolService  implements MainService<Tool> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();

    @Override
    public Tool add(Tool tool) throws SQLException {
        Tool c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(tool);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public void set(Tool tool) throws SQLException {
        em.getTransaction().begin();
        em.merge(tool);
        em.getTransaction().commit();
    }

    @Override
    public Tool get(Long id) throws SQLException {
        return em.find(Tool.class, id);
    }

    @Override
    public void del(Tool tool) throws SQLException {
        em.getTransaction().begin();
        em.remove(tool);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<Tool> getAll() throws SQLException {
        TypedQuery<Tool> namedQuery = em.createNamedQuery("Tool.getAll", Tool.class);
        return namedQuery.getResultList();
    }
}
