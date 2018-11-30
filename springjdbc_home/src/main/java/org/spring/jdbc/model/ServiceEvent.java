package org.spring.jdbc.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceEvent {

	private int id;
	private User organizer;
	private Community community;
	private String description;
	private Calendar date;
	private String name;
	private List<Project> projects;
}
