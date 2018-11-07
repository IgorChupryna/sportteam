package hibernate.model;

import javax.persistence.*;

@Entity
@SuppressWarnings("all")
@Table(name = "UNIVERSITY")
public class University {
    @Id
    @GeneratedValue
    @Column(name="UNIVERSITY_ID")
    private  long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTRY")
    private String country;

    public University() {
    }

    public University(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
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
}
