package jpaproject.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "service_event")
@NamedQuery(name = "ServiceEvent.getAll", query = "select s from ServiceEvent s")
public class ServiceEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizer")
	private User organizer;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Community community;

    @Column(name="description",length = 255)
	private String description;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@Column(name="name",length = 32)
	private String name;


	public ServiceEvent(User organizer, Community community, String description, Calendar date, String name) {
		this.organizer = organizer;
		this.community = community;
		this.description = description;
		this.date = date;
		this.name = name;
	}

	public ServiceEvent() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOrganizer() {
		return organizer;
	}

	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
