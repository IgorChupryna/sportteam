package hibernate.model;


import javax.persistence.*;

@Entity
@SuppressWarnings("all")
@Table(name="DIRECTOR")
public class Director {

    @Id
    @GeneratedValue
    @Column(name="DIRECTOR_ID")
    private  long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @OneToOne(mappedBy = "director", cascade = CascadeType.ALL)
    private DirectorAddress directorAddress;

    public Director() {
    }

    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public DirectorAddress getDirectorAddress() {
        return directorAddress;
    }

    public void setDirectorAddress(DirectorAddress directorAddress) {
        this.directorAddress = directorAddress;
    }
}
