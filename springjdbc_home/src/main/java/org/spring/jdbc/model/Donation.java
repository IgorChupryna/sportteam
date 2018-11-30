package org.spring.jdbc.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Donation {

	private int id;
	private double amount;
	private Date dateAdded;
	private Project project;
	private User user;
}
