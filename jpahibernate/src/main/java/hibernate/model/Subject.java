package hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuppressWarnings("all")
@Table(name="SUBJECT")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name="SUBJECT_ID")
    private  long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }

    public Subject(String name, List<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teachers=" + teachers +
                '}';
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
