package com.sportteam.spring.model;

import java.util.List;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email")
	private String email;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;

	private List<Skill> skills;
	private List<Tool> tools;
	private List<Comment> comments ;
	private List<Community> communityMemberships;
	private List<Community> communitiesCreated;
	private List<Donation> donations ;
	private List<Project> projectsSubmitted ;
	private List<Project> projectsOrganized ;
	private List<Project> projectsVolunteered;
	private List<ServiceEvent> serviceEventsOrganized ;

}
