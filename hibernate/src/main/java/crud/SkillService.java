package crud;

import conn.HibernateUtil;
import entity.Skill;
import interfaces.MainService;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public class SkillService implements MainService<Skill> {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public Integer add(Skill skill) throws SQLException {
        session.beginTransaction();
        Integer id=null;
        try {
            id = (Integer) session.save(skill);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void set(Skill skill) throws SQLException {
        session.beginTransaction();
        try {
            session.saveOrUpdate(skill);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public Skill get(Long id) throws SQLException {
        return (Skill)session.get(Skill.class, id);
    }

    @Override
    public void del(Skill skill) throws SQLException {
        session.beginTransaction();
        session.delete(skill);
        session.getTransaction().commit();
    }

    @Override
    public void del(Long id) throws SQLException {
        Query query = session.createQuery("delete Skill where id = :ID");
        query.setParameter("ID", id);
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Skill> skills = (List<Skill>) session.createQuery("FROM Skill s").list();
        session.getTransaction().commit();
        return skills;
    }
}
