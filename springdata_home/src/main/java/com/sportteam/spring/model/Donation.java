package com.sportteam.spring.model;

import java.sql.Date;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Donation")
public class Donation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private double amount;
	@Column(name = "date")
	private Date dateAdded;

//
//	private Project project;
//	private User user;
}
