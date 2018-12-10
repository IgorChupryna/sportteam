package org.spring.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String login;
    private String name;
    private int age;
    private double salary;

}
