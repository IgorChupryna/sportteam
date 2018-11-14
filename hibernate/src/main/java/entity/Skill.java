package entity;
import javax.persistence.*;

@Entity
@Table(name="SKILL")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SKILL_ID")
	private Long id;

	@Column(name="NAME",length = 32)
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