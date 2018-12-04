package org.spring.jdbc.service;

import org.springframework.jdbc.core.JdbcTemplate;

public class MainService {

    private final JdbcTemplate jdbcTemplate;

    public MainService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @SuppressWarnings("all")
    public void initDB() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS Comment");
        jdbcTemplate.execute("CREATE TABLE Comment(id INT PRIMARY KEY AUTO_INCREMENT, dateAdded DATE, text VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Community");
        jdbcTemplate.execute("CREATE TABLE Community(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Donation");
        jdbcTemplate.execute("CREATE TABLE Donation(id INT PRIMARY KEY AUTO_INCREMENT, amount BIGINT(64), dateAdded DATE )");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Project");
        jdbcTemplate.execute("CREATE TABLE Project(id INT PRIMARY KEY AUTO_INCREMENT,  address1 VARCHAR(255), address2 VARCHAR(255),city VARCHAR(255), dateAdded VARCHAR(255), description VARCHAR(255), email VARCHAR(255),firstname  VARCHAR(255), lastname VARCHAR(255), phone VARCHAR(255), state VARCHAR(255), zip VARCHAR(255),title VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Project_Donations");
        jdbcTemplate.execute("CREATE TABLE Project_Donations(projectId INT, donationId INT, PRIMARY KEY(projectId, donationId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Project_Comments");
        jdbcTemplate.execute("CREATE TABLE Project_Comments(projectId INT, commentId INT, PRIMARY KEY(projectId, commentId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Project_imageUrls");
        jdbcTemplate.execute("CREATE TABLE Project_imageUrls(id INT PRIMARY KEY, projectId INT, path VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Project_serviceEvent");
        jdbcTemplate.execute("CREATE TABLE Project_serviceEvent(projectId INT, serviceEventId INT,PRIMARY KEY(projectId, serviceEventId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Service_EO");
        jdbcTemplate.execute("CREATE TABLE Service_EO(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255),description VARCHAR(255), date DATE )");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Service_EO_community");
        jdbcTemplate.execute("CREATE TABLE Service_EO_community(serviceEOId INT, communityId INT, PRIMARY KEY(serviceEOId, communityId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Skills");
        jdbcTemplate.execute("CREATE TABLE Skills(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Tools");
        jdbcTemplate.execute("CREATE TABLE Tools(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users");
        jdbcTemplate.execute("CREATE TABLE Users(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255), phone VARCHAR(255))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users_Tools");
        jdbcTemplate.execute("CREATE TABLE Users_Tools(userId INT, toolId INT, PRIMARY KEY(userId, toolId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users_Skills");
        jdbcTemplate.execute("CREATE TABLE Users_Skills(userId INT, skillId INT, PRIMARY KEY(userId, skillId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users_Comments");
        jdbcTemplate.execute("CREATE TABLE Users_Comments(userId INT, commentId INT, PRIMARY KEY(userId, commentId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users_Community_Memberships");
        jdbcTemplate.execute("CREATE TABLE Users_Community_Memberships(userId INT, communityId INT, PRIMARY KEY(userId, communityId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users_Community_Created");
        jdbcTemplate.execute("CREATE TABLE Users_Community_Created(userId INT, communityId INT, PRIMARY KEY(userId, communityId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS User_Donation");
        jdbcTemplate.execute("CREATE TABLE User_Donation(userId INT, donationId INT, PRIMARY KEY(userId, donationId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS User_Service_EO");
        jdbcTemplate.execute("CREATE TABLE User_Service_EO(userId INT, serviceEOId INT, PRIMARY KEY(userId, serviceEOId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS User_projectsSubmitted");
        jdbcTemplate.execute("CREATE TABLE User_projectsSubmitted(userId INT, projectId INT, PRIMARY KEY(userId, projectId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS User_projectsOrganized");
        jdbcTemplate.execute("CREATE TABLE User_projectsOrganized(userId INT, projectId INT, PRIMARY KEY(userId, projectId))");
        jdbcTemplate.execute("DROP TABLE IF EXISTS User_projectsVolunteered");
        jdbcTemplate.execute("CREATE TABLE User_projectsVolunteered(userId INT, projectId INT, PRIMARY KEY(userId, projectId))");

    }


}
