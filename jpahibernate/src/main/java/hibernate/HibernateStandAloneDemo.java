package hibernate;

import hibernate.HibernateUtil;
import hibernate.model.Student;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class HibernateStandAloneDemo {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int studentId1 = saveStudent("Sam", "Disila", "decde");

        //List<Student> students = getAllStudents();
    }

    public static List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) session.createQuery("FROM Student s Order BY s.firstName ASC").list();
        session.getTransaction().commit();
        return students;
    }

    public static int saveStudent(String firstName, String lastName, String section) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setSection(section);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer id = null;

        try {
            id = (Integer) session.save(student);
            session.getTransaction().commit();

        } catch (Throwable ex) {
            session.getTransaction().rollback();
        }
        return id;
    }

    public static void updateStudent(int id, String section) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Student student = (Student) session.get(Student.class, id);
        student.setSection(section);
        session.getTransaction().commit();
    }

    public static void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        session.delete(id);
        session.getTransaction().commit();
    }
}
