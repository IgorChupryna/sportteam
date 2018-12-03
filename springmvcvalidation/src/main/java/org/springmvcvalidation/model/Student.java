package org.springmvcvalidation.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable{

    @Size(min=3,max=30)
    private String firstName;

    @Size(min=3,max=30)
    private String lastName;
    @NotEmpty
    private  String sex;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date dob;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String section;

    @NotEmpty
    private String country;

    public boolean isFirstAttempt() {
        return firstAttempt;
    }

    public void setFirstAttempt(boolean firstAttempt) {
        this.firstAttempt = firstAttempt;
    }

    private boolean firstAttempt;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @NotEmpty
    private List<String> subjects = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String sex, Date dob, String email, String section, String country, boolean firstAttemt, List<String> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dob = dob;
        this.email = email;
        this.section = section;
        this.country = country;

        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", section='" + section + '\'' +
                ", country='" + country + '\'' +

                ", subjects=" + subjects +
                '}';
    }
}
