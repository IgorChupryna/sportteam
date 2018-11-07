package hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="STUDENT")
public class Student {
    @Id
    @Column(name = "ADDRESS_ID")
    private long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "SECTION")
    private String section;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;
    @ManyToOne(optional = false)

    //@JoinColumn(name)
    //private University university;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", section='" + section + '\'' +
                '}';
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
