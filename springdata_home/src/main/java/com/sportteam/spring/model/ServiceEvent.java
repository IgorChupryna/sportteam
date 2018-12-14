package com.sportteam.spring.model;

import java.util.Calendar;
import java.util.List;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Service_Event")
public class ServiceEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "date")
	private Calendar date;

	private User organizer;
	private Community community;
	private List<Project> projects;
}
