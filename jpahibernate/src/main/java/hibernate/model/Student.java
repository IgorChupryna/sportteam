package hibernate.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable{
    private int id;
    private String firstName;
    private String lastName;
    private String section;


    @Override
    public String toString() {
        return "Student{" +
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

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String section) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.section = section;
    }
}
