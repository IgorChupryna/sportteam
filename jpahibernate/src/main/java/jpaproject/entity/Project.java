package jpaproject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "project")
@NamedQuery(name = "Project.getAll", query = "select s from Project s")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address1", length = 255)
    private String address1;

    @Column(name = "address2", length = 255)
    private String address2;

    @Column(name = "city", length = 64)
    private String city;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateAdded;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "email", length = 32)
    private String email;

    @Column(name = "firstName", length = 32)
    private String firstName;

    @Column(name = "lastName", length = 32)
    private String lastName;

    @Column(name = "phone", length = 32)
    private String phone;

    @Column(name = "state", length = 32)
    private String state;

    @Column(name = "zip", length = 32)
    private String zip;

    @Column(name = "title", length = 64)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer")
    private User organizer = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitter")
    private User submitter = null;


    @ManyToMany(mappedBy = "projectsVolunteered")
    private List<User> volunteers = new ArrayList<User>();


    @Column(name="image urls")
    @ElementCollection(targetClass=String.class)
    private List<String> imageUrls = new ArrayList<String>();

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Donation> donations = new ArrayList<Donation>();

    public Project(String address1, String address2, String city, Calendar dateAdded, String description, String email, String firstName, String lastName, String phone, String state, String zip, String title, User organizer, User submitter, List<User> volunteers, List<String> imageUrls, List<Comment> comments, List<Donation> donations) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.dateAdded = dateAdded;
        this.description = description;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.state = state;
        this.zip = zip;
        this.title = title;
        this.organizer = organizer;
        this.submitter = submitter;
        this.volunteers = volunteers;
        this.imageUrls = imageUrls;
        this.comments = comments;
        this.donations = donations;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public List<User> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<User> volunteers) {
        this.volunteers = volunteers;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", dateAdded=" + dateAdded +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", title='" + title + '\'' +
                ", organizer=" + organizer +
                ", submitter=" + submitter +
                ", volunteers=" + volunteers +
                ", imageUrls=" + imageUrls +
                ", comments=" + comments +
                ", donations=" + donations +
                '}';
    }
}
