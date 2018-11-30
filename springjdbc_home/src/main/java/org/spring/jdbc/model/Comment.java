package org.spring.jdbc.model;

import lombok.*;

import java.util.Calendar;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
	private int id;
	private Calendar dateAdded;
	private User submitter;
	private String text;
	private Project project;

}
