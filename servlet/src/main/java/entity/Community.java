package entity;

import entity.User;

import javax.persistence.*;

@Entity
@Table(name="community")
@NamedQuery(name="Community.getAll", query="select c from Community c")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name",length = 32)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")
	private User creator = null;


	public Community(String name, User creator) {
		this.name = name;
		this.creator = creator;
	}

	public Community() {
	}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
