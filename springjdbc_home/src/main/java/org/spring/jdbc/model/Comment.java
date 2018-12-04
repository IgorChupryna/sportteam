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
	private String text;
	private User submitter;
	private Project project;

}
