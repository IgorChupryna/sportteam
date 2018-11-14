package crud;


import conn.HibernateUtil;
import entity.Project;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public class ProjectService implements MainService<Project> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(Project project) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(project);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(Project project) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(project);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Project get(Long id) throws SQLException {
        return (Project)session.get(Project.class, id);
    }

    @Override
    public void del(Project project) throws SQLException {
        session.beginTransaction();
        session.delete(project);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete Project where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<Project> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Project> projects = (List<Project>) session.createQuery("FROM Project s").list();
        session.getTransaction().commit();
        return projects;
    }
}
