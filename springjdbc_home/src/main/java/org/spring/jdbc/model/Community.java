package org.spring.jdbc.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Community {
	private int id;
	private String name;
	private User creator = null;
}
