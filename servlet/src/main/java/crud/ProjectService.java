package crud;


import entity.Project;
import interfaces.MainService;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class ProjectService implements MainService<Project> {
    public EntityManager em = Persistence.createEntityManagerFactory("SPORTTEAM").createEntityManager();
    @Override
    public Project add(Project project) throws SQLException {
        Project c = null;
        em.getTransaction().begin();
        try {
            c = em.merge(project);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | RollbackException ex) {
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public void set(Project project) throws SQLException {
        em.getTransaction().begin();
        em.merge(project);
        em.getTransaction().commit();
    }

    @Override
    public Project get(Long id) throws SQLException {
        return em.find(Project.class, id);
    }

    @Override
    public void del(Project project) throws SQLException {
        em.getTransaction().begin();
        em.remove(project);
        em.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public List<Project> getAll() throws SQLException {
        TypedQuery<Project> namedQuery = em.createNamedQuery("Project.getAll", Project.class);
        return namedQuery.getResultList();
    }
}
