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
public class Project {

	private int id;
	private User organizer = null;
	private String address1;
	private String address2;
	private String city;
	private Calendar dateAdded;
	private String description;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String state;
	private User submitter = null;
	private String zip;
	private List<User> volunteers = new ArrayList<User>();
    private List<String> imageUrls = new ArrayList<String>();
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Donation> donations = new ArrayList<Donation>();
    private String title;
}
