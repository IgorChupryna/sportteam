package jpaproject.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="comment")
@NamedQuery(name="Comment.getAll", query="select s from Comment s")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar dateAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitter")
	private User submitter;

    @Column(name="text",length = 32)
	private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
	private Project project;


    public Comment(Calendar dateAdded, User submitter, String text, Project project) {
        this.dateAdded = dateAdded;
        this.submitter = submitter;
        this.text = text;
        this.project = project;
    }

    public Comment() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
