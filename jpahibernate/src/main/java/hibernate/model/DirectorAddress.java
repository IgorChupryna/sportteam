package hibernate.model;



import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DIRECTOR_ADDRESS")
public class DirectorAddress {

    @Id
    @Column(name="ADDRESS_ID")
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen",strategy = "foreign",  parameters=@Parameter(name="property",value="director"))
    private  long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Director director;

    public DirectorAddress() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "DirectorAddress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", director=" + director +
                '}';
    }
}
