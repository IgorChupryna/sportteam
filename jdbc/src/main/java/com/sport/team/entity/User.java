package com.sport.team.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class User.
 */
public class User {
	
	/** The id. */
	private int id;

	/** The email. */
	private String email;

	/** The name. */
	private String name;

	/** The phone. */
	private String phone;

	/** The skills. */
	private List<Skill> skills;

	/** The tools. */
	private List<Tool> tools;

	/** The comments. */
	private List<Comment> comments ;


	/** The community memberships. */
	private List<Community> communityMemberships;

	/** The communities created. */
	private List<Community> communitiesCreated;
	
	/** The donations. */
	private List<Donation> donations ;
	
	/** The projects submitted. */
	private List<Project> projectsSubmitted = new ArrayList<Project>();
	
	/** The projects organized. */
	private List<Project> projectsOrganized = new ArrayList<Project>();
	
	/** The projects volunteered. */
	private List<Project> projectsVolunteered = new ArrayList<Project>();
	

	
	/** The service events organized. */
	private List<ServiceEvent> serviceEventsOrganized = new ArrayList<ServiceEvent>();







	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the community memberships.
	 *
	 * @return the community memberships
	 */
	public List<Community> getCommunityMemberships() {
		return communityMemberships;
	}
	
	/**
	 * Sets the community memberships.
	 *
	 * @param communityMemberships the new community memberships
	 */
	public void setCommunityMemberships(List<Community> communityMemberships) {
		this.communityMemberships = communityMemberships;
	}
	
	/**
	 * Gets the communities created.
	 *
	 * @return the communities created
	 */
	public List<Community> getCommunitiesCreated() {
		return communitiesCreated;
	}
	
	/**
	 * Sets the communities created.
	 *
	 * @param communitiesCreated the new communities created
	 */
	public void setCommunitiesCreated(List<Community> communitiesCreated) {
		this.communitiesCreated = communitiesCreated;
	}
	
	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<Skill> getSkills() {
		return skills;
	}
	
	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	/**
	 * Gets the tools.
	 *
	 * @return the tools
	 */
	public List<Tool> getTools() {
		return tools;
	}
	
	/**
	 * Sets the tools.
	 *
	 * @param tools the new tools
	 */
	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}
	
	/**
	 * Gets the projects submitted.
	 *
	 * @return the projects submitted
	 */
	public List<Project> getProjectsSubmitted() {
		return projectsSubmitted;
	}
	
	/**
	 * Sets the projects submitted.
	 *
	 * @param projectsSubmitted the new projects submitted
	 */
	public void setProjectsSubmitted(List<Project> projectsSubmitted) {
		this.projectsSubmitted = projectsSubmitted;
	}
	
	/**
	 * Gets the projects organized.
	 *
	 * @return the projects organized
	 */
	public List<Project> getProjectsOrganized() {
		return projectsOrganized;
	}
	
	/**
	 * Sets the projects organized.
	 *
	 * @param projectsOrganized the new projects organized
	 */
	public void setProjectsOrganized(List<Project> projectsOrganized) {
		this.projectsOrganized = projectsOrganized;
	}
	
	/**
	 * Gets the projects volunteered.
	 *
	 * @return the projects volunteered
	 */
	public List<Project> getProjectsVolunteered() {
		return projectsVolunteered;
	}
	
	/**
	 * Sets the projects volunteered.
	 *
	 * @param projectsVolunteered the new projects volunteered
	 */
	public void setProjectsVolunteered(List<Project> projectsVolunteered) {
		this.projectsVolunteered = projectsVolunteered;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	/**
	 * Gets the donations.
	 *
	 * @return the donations
	 */
	public List<Donation> getDonations() {
		return donations;
	}
	
	/**
	 * Sets the donations.
	 *
	 * @param donations the new donations
	 */
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	/**
	 * Gets the service events organized.
	 *
	 * @return the service events organized
	 */
	public List<ServiceEvent> getServiceEventsOrganized() {
		return serviceEventsOrganized;
	}
	
	/**
	 * Sets the service events organized.
	 *
	 * @param serviceEventsOrganized the new service events organized
	 */
	public void setServiceEventsOrganized(List<ServiceEvent> serviceEventsOrganized) {
		this.serviceEventsOrganized = serviceEventsOrganized;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "\nName: " ).append( name );
		sb.append( "\nEmail: " ).append( email );
		sb.append( "\nPhone: " ).append( phone );

		return sb.toString();
	}
}
