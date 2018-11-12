package hibernate;

import hibernate.HibernateUtil;
import hibernate.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class HibernateStandAloneDemo {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

//
//        Student student2 = new Student("Joshua", "Brill", "Science");
//        Student student3 = new Student("Peter", "Pan", "Physics");
//
//        University university = new University("Tarasha Shevchenka", "ENGLAND");
//
//        student2.setUniversity(university);
//        student3.setUniversity(university);
//
//        session.beginTransaction();
//
//        session.persist(university);
//        session.persist(student2);
//        session.persist(student3);
//
//        List<Student> students = (List<Student>)session.createQuery("from Student ").list();
//        for(Student s: students){
//            System.out.println("Details : "+s);
//            System.out.println("Student University Details: "+s.getUniversity());
//        }
//
//        session.getTransaction().commit();
//        session.close();


//
//        Teacher teacher2 = new Teacher("Joshua", "Brill", "Science");
//        Teacher teacher3 = new Teacher("Peter", "Pan", "Physics");
//
//        TeacherUniversity teacherUniversity = new TeacherUniversity("CAMBRIDGE", "ENGLAND");
//        TeacherAddress teacherAddress = new TeacherAddress("Independence Street", "London", "England" );
//        List<Teacher> teachers = new ArrayList<Teacher>();
//
//        teacher2.setTeacherUniversity(teacherUniversity);
//        teacher2.setTeacherAddress(teacherAddress);
//
//        teacher3.setTeacherUniversity(teacherUniversity);
//        teacher3.setTeacherAddress(teacherAddress);
//
//        teachers.add(teacher2);
//        teachers.add(teacher3);
//
//
//        teacherUniversity.setTeachers(teachers);
//
//        session.beginTransaction();
//
//        session.persist(teacherAddress);
//
//        session.persist(teacherUniversity);
//
//        List<Teacher> teacherList = (List<Teacher>) session.createQuery(
//                "from Teacher ").list();
//        for (Teacher t : teacherList) {
//            System.out.println("Teacher Details : " + t);
//            System.out.println("Teacher University Details: "
//                    + t.getTeacherUniversity());
//        }
//
//        session.getTransaction().commit();
//        session.close();

//        Student student4 = new Student("Sam1","Disilva", "Maths");
//        Student student5 = new Student("Joshua1", "Brill", "Maths");
//
//        Subject subject1 = new Subject("Economics1");
//        Subject subject2 = new Subject("Politics1");
//        Subject subject3 = new Subject("Foreign Affairs1");
//
//        University university = new University("Oxford1", "UK");
//
//        student4.setUniversity(university);
//
//        student5.setUniversity(university);
//
//        //Student4 have 3 subjects
//        student4.getSubjects().add(subject1);
//        student4.getSubjects().add(subject2);
//        student4.getSubjects().add(subject3);
//
//        //Student5 have 2 subjects
//        student5.getSubjects().add(subject1);
//        student5.getSubjects().add(subject2);
//
//        session.beginTransaction();
//
//        session.persist(university);
//
//        session.persist(student4);
//        session.persist(student5);
//
//        session.getTransaction().commit();
//        session.close();


        Teacher teacher4 = new Teacher("Sam1", "Disilva", "Maths");
        Teacher teacher5 = new Teacher("Joshua1", "Brill", "Maths");

        Subject subject4 = new Subject("Economics2");
        Subject subject5 = new Subject("Politics2");
        Subject subject6 = new Subject("Foreign Affairs2");

        TeacherAddress teacherAddress = new TeacherAddress("Independence Street1", "London", "Englang");

        TeacherUniversity teacherUniversity = new TeacherUniversity("Oxford1", "UK");

        //Teacher4 have 3 subjects
        teacher4.getSubjects().add(subject4);
        teacher4.getSubjects().add(subject5);
        teacher4.getSubjects().add(subject6);

        //Teacher5 have 2 subjects
        teacher5.getSubjects().add(subject4);
        teacher5.getSubjects().add(subject5);

        session.beginTransaction();

        session.persist(teacherAddress);

        teacher4.setTeacherAddress(teacherAddress);

        teacher5.setTeacherAddress(teacherAddress);

        session.persist(teacherUniversity);

        teacher4.setTeacherUniversity(teacherUniversity);

        teacher5.setTeacherUniversity(teacherUniversity);

        session.persist(teacher4);
        session.persist(teacher5);

        session.getTransaction().commit();
        session.close();
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
