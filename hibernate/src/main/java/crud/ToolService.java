package crud;

import conn.HibernateUtil;
import entity.Tool;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class ToolService  implements MainService<Tool> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(Tool tool) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(tool);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(Tool tool) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(tool);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Tool get(Long id) throws SQLException {
        return (Tool)session.get(Tool.class, id);
    }

    @Override
    public void del(Tool tool) throws SQLException {
        session.beginTransaction();
        session.delete(tool);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete Tool where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<Tool> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Tool> tools = (List<Tool>) session.createQuery("FROM Tool s Order BY s.name ASC").list();
        session.getTransaction().commit();
        return tools;
    }




}
