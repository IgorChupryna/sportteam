package hibernate.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TEACHER")
@SuppressWarnings("all")
public class Teacher {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "SECTION",nullable = false)
    private String section;

    @OneToOne
    @JoinColumn(name="HOME_ADDRESS_ID")
    private TeacherAddress teacherAddress;



    @ManyToOne(optional = false)
    @JoinColumn(name="UNIVERSITY_ID")
    private TeacherUniversity teacherUniversity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TEACHER_SUBJECT",
            joinColumns = { @JoinColumn(name = "TEACHER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID") })
    private List<Subject> subjects = new ArrayList<Subject>();


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String section) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.section = section;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", section='" + section + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public TeacherAddress getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(TeacherAddress teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public TeacherUniversity getTeacherUniversity() {
        return teacherUniversity;
    }

    public void setTeacherUniversity(TeacherUniversity teacherUniversity) {
        this.teacherUniversity = teacherUniversity;
    }
}
