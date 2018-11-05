package hibernate;


import hibernate.model.Teacher;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("unused")
public class TeaherService {
    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public static List<Teacher> getAllTeachers() {
        
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Teacher> teachers = (List<Teacher>) session.createQuery("FROM Teacher s Order BY s.firstName ASC").list();
        session.getTransaction().commit();
        return teachers;
    }

    public static int saveTeacher(String firstName, String lastName, String section) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setSection(section);
        
        session.beginTransaction();
        Integer id = null;

        try {
            id = (Integer) session.save(teacher);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    public static void updateTeacher(int id, String section) {
    
        session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class, id);
        teacher.setSection(section);
        session.getTransaction().commit();
    }

    public static void deleteTeacher(int id) {
        
        session.beginTransaction();
        Teacher teacher = (Teacher) session.get(Teacher.class, id);
        session.delete(id);
        session.getTransaction().commit();
    }
    
}
