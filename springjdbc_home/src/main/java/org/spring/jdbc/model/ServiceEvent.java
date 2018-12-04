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
	private String name;
	private String description;
	private Calendar date;

	private User organizer;
	private Community community;
	private List<Project> projects;
}
