package entity;
import javax.persistence.*;

@Entity
@Table(name="skill")
@NamedQuery(name="Skill.getAll", query="select s from Skill s")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="name",length = 32)
	private String name;

	public Skill() {
	}

	public Skill(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString();
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
}