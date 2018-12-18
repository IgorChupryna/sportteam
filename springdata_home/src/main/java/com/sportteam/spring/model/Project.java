package com.sportteam.spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String address1;
    @Column
    private String address2;
    @Column
    private String city;
    @Column(name = "date")
    private Calendar dateAdded;
    @Column
    private String description;
    @Column
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String lastName;
    @Column
    private String phone;
    @Column
    private String state;
    @Column
    private String zip;
    @Column
    private String title;

//    private User organizer = null;
//    private User submitter = null;
//    private List<User> volunteers = new ArrayList<User>();
//    private List<Comment> comments = new ArrayList<Comment>();
//    private List<Donation> donations = new ArrayList<Donation>();

}
