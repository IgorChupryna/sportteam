package com.sport.team.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The Class ServiceEvent.
 */
public class ServiceEvent {
	
	/** The id. */
	private int id;

	/** The organizer. */
	private User organizer;

	/** The community. */
	private Community community;

	/** The description. */
	private String description;

	/** The date. */
	private Calendar date;

	/** The name. */
	private String name;

	/** The projects. */
	private List<Project> projects = new ArrayList<Project>();

	/**
	 * Gets the organizer.
	 *
	 * @return the organizer
	 */
	public User getOrganizer() {
		return organizer;
	}

	/**
	 * Sets the organizer.
	 *
	 * @param organizer the new organizer
	 */
	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	/**
	 * Gets the community.
	 *
	 * @return the community
	 */
	public Community getCommunity() {
		return community;
	}

	/**
	 * Sets the community.
	 *
	 * @param community the new community
	 */
	public void setCommunity(Community community) {
		this.community = community;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

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
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * Sets the projects.
	 *
	 * @param projects the new projects
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
