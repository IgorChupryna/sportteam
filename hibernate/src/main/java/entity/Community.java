package entity;

import javax.persistence.*;

@Entity
@Table(name="COMMUNITY")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COMMUNITY_ID")
    private Long id;

    @Column(name="NAME",length = 32)
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
