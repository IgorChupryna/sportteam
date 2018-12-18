package com.sportteam.spring.model;

import java.util.Calendar;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "date")
	private Calendar dateAdded;
	@Column(name = "text")
	private String text;
//
//	private User submitter;
//	private Project project;

}
