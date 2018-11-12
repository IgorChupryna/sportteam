package hibernate.model;

import javax.persistence.*;
import java.util.List;

@Entity@Table(name="TEACHER_UNIVERSITY")
@SuppressWarnings("all")
public class TeacherUniversity {

    @Id
    @GeneratedValue
    @Column(name="UNIVERSITY_ID")
    private  long id;

    @Column(name = "NAME")
    private String name;


    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(mappedBy = "teacherUniversity", cascade = CascadeType.ALL)
    private List<Teacher> teachers;

    public TeacherUniversity() {
    }

    public TeacherUniversity(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
