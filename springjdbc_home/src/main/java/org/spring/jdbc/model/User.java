package org.spring.jdbc.model;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	private int id;
	private String email;
	private String name;
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
