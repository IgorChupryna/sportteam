package entity;

import entity.Project;
import entity.User;

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
	private entity.User submitter;

    @Column(name="text",length = 32)
	private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
	private entity.Project project;


    public Comment(Calendar dateAdded, entity.User submitter, String text, entity.Project project) {
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

    public entity.User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(entity.User submitter) {
        this.submitter = submitter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public entity.Project getProject() {
        return project;
    }

    public void setProject(entity.Project project) {
        this.project = project;
    }
}
