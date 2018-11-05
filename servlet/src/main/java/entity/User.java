package entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.getAll", query = "select s from User s")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "address1", length = 32)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Tool> tools;

    @OneToMany(
            mappedBy = "submitter",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments;

    @OneToMany(
            mappedBy = "creator",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Community> communityMemberships;

    @OneToMany(
            mappedBy = "creator",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Community> communitiesCreated;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Donation> donations;

    @OneToMany(
            mappedBy = "submitter",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Project> projectsSubmitted;

    @OneToMany(
            mappedBy = "organizer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Project> projectsOrganized;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "projects_volunteered",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projectsVolunteered;


    @OneToMany(
            mappedBy = "organizer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ServiceEvent> serviceEventsOrganized;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", skills=" + skills +
                ", tools=" + tools +
                ", comments=" + comments +
                ", communityMemberships=" + communityMemberships +
                ", communitiesCreated=" + communitiesCreated +
                ", donations=" + donations +
                ", projectsSubmitted=" + projectsSubmitted +
                ", projectsOrganized=" + projectsOrganized +
                ", projectsVolunteered=" + projectsVolunteered +
                ", serviceEventsOrganized=" + serviceEventsOrganized +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Community> getCommunityMemberships() {
        return communityMemberships;
    }

    public void setCommunityMemberships(List<Community> communityMemberships) {
        this.communityMemberships = communityMemberships;
    }

    public List<Community> getCommunitiesCreated() {
        return communitiesCreated;
    }

    public void setCommunitiesCreated(List<Community> communitiesCreated) {
        this.communitiesCreated = communitiesCreated;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<Project> getProjectsSubmitted() {
        return projectsSubmitted;
    }

    public void setProjectsSubmitted(List<Project> projectsSubmitted) {
        this.projectsSubmitted = projectsSubmitted;
    }

    public List<Project> getProjectsOrganized() {
        return projectsOrganized;
    }

    public void setProjectsOrganized(List<Project> projectsOrganized) {
        this.projectsOrganized = projectsOrganized;
    }

    public List<Project> getProjectsVolunteered() {
        return projectsVolunteered;
    }

    public void setProjectsVolunteered(List<Project> projectsVolunteered) {
        this.projectsVolunteered = projectsVolunteered;
    }

    public List<ServiceEvent> getServiceEventsOrganized() {
        return serviceEventsOrganized;
    }

    public void setServiceEventsOrganized(List<ServiceEvent> serviceEventsOrganized) {
        this.serviceEventsOrganized = serviceEventsOrganized;
    }

    public User(String email, String name, String phone, List<Skill> skills, List<Tool> tools, List<Comment> comments, List<Community> communityMemberships, List<Community> communitiesCreated, List<Donation> donations, List<Project> projectsSubmitted, List<Project> projectsOrganized, List<Project> projectsVolunteered, List<ServiceEvent> serviceEventsOrganized) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.skills = skills;
        this.tools = tools;
        this.comments = comments;
        this.communityMemberships = communityMemberships;
        this.communitiesCreated = communitiesCreated;
        this.donations = donations;
        this.projectsSubmitted = projectsSubmitted;
        this.projectsOrganized = projectsOrganized;
        this.projectsVolunteered = projectsVolunteered;
        this.serviceEventsOrganized = serviceEventsOrganized;
    }

    public User() {
    }
}
