package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Comment;
import com.sport.team.entity.Donation;
import com.sport.team.entity.Project;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectService implements MainService<Project> {

    /**
     * Inits the db User tables.
     *
     * @throws SQLException the SQL exception
     */
    public static void initDb() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project(id INT PRIMARY KEY,  address1 VARCHAR(255), address2 VARCHAR(255),city VARCHAR(255), dateAdded VARCHAR(255), description VARCHAR(255), email VARCHAR(255),firstname  VARCHAR(255), lastname VARCHAR(255), phone VARCHAR(255), state VARCHAR(255), zip VARCHAR(255),title VARCHAR(255))");

            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_Donations");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_Donations(projectId INT, donationId INT, PRIMARY KEY(projectId, donationId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_Comments");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_Comments(projectId INT, commentId INT, PRIMARY KEY(projectId, commentId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_imageUrls");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_imageUrls(id INT PRIMARY KEY, projectId INT, path VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_serviceEvent");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_serviceEvent(projectId INT, serviceEventId INT,PRIMARY KEY(projectId, serviceEventId))");
            stmt.executeUpdate();
            stmt.close();


            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    /**
     * Insert project.
     *
     * @param project the project
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Project project) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Project VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, project.getId());
            stmt.setString(2, project.getAddress1());
            stmt.setString(3, project.getAddress2());
            stmt.setString(4, project.getCity());
            stmt.setDate(5, new java.sql.Date(project.getDateAdded().getTimeInMillis()));
            stmt.setString(6, project.getDescription());
            stmt.setString(7, project.getEmail());
            stmt.setString(8, project.getFirstName());
            stmt.setString(9, project.getLastName());
            stmt.setString(10, project.getPhone());
            stmt.setString(11, project.getState());
            stmt.setString(12, project.getZip());
            stmt.setString(13, project.getTitle());
            stmt.executeUpdate();
            stmt.close();

            addTables(project);
        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Update project.
     *
     * @param project the project
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Project project) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();
            stmt = conn.prepareStatement("UPDATE Project  SET Project.address1=?, Project.address2=?, Project.dateAdded=?," +
                    "Project.phone=?, Project.email=?, Project.city=?, Project.description=?, Project.firstname=?, Project.lastname=?, Project.state=?, Project.title=?, Project.zip=?" +
                    " WHERE  Project.id=?");

            stmt.setString(1, project.getAddress1());
            stmt.setString(2, project.getAddress2());
            stmt.setString(3, project.getCity());
            stmt.setDate(4, new java.sql.Date(project.getDateAdded().getTimeInMillis()));
            stmt.setString(5, project.getDescription());
            stmt.setString(6, project.getEmail());
            stmt.setString(7, project.getFirstName());
            stmt.setString(8, project.getLastName());
            stmt.setString(9, project.getPhone());
            stmt.setString(10, project.getState());
            stmt.setString(11, project.getZip());
            stmt.setString(12, project.getTitle());
            stmt.setInt(13, project.getId());

            stmt.executeUpdate();
            stmt.close();

            delTables(project);
            addTables(project);
        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Gets the project.
     *
     * @param id the id
     * @return the project
     * @throws SQLException the SQL exception
     */
    @Override
    public Project get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Project.id, Project.address1, Project.address2, Project.dateAdded," +
                    "Project.phone, Project.email, Project.city, Project.description, Project.firstname, Project.lastname, Project.state, Project.title, Project.zip" +
                    " FROM Project WHERE Project.id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();

            Project project = new Project();
            project.setId(rs.getInt(1));
            project.setAddress1(rs.getString(2));
            project.setAddress2(rs.getString(3));
            project.setDateAdded(DonationService.toCalendar(rs.getDate(4)));
            project.setPhone(rs.getString(5));
            project.setEmail(rs.getString(6));
            project.setCity(rs.getString(7));
            project.setDescription(rs.getString(8));
            project.setFirstName(rs.getString(9));
            project.setLastName(rs.getString(10));
            project.setState(rs.getString(11));
            project.setTitle(rs.getString(12));
            project.setZip(rs.getString(13));

            rs.close();
            stmt.close();

            project.setVolunteers(new ArrayList<User>());

            stmt = conn.prepareStatement("SELECT Users.id FROM Users, User_projectsVolunteered "
                    + "WHERE User_projectsVolunteered.projectId=? AND User_projectsVolunteered.userId=Users.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new UserService().get(rs.getInt(1));
                project.getVolunteers().add(user);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Users.id FROM Users, User_projectsSubmitted "
                    + "WHERE User_projectsSubmitted.projectId=? AND User_projectsSubmitted.userId=Users.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            User submitter = new UserService().get(rs.getInt(1));
            project.setSubmitter(submitter);
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Users.id FROM Users, User_projectsOrganized "
                    + "WHERE User_projectsOrganized.projectId=? AND User_projectsOrganized.userId=Users.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            User organizer = new UserService().get(rs.getInt(1));
            project.setOrganizer(organizer);
            rs.close();
            stmt.close();

            project.setImageUrls(new ArrayList<>());
            stmt = conn.prepareStatement("SELECT Project_imageUrls.path FROM Project_imageUrls "
                    + "WHERE Project_imageUrls.projectId=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                project.getImageUrls().add(rs.getString(3));
            }
            rs.close();
            stmt.close();

            project.setComments(new ArrayList<Comment>());

            stmt = conn.prepareStatement("SELECT Comment.id FROM Comment, Project_Comments "
                    + "WHERE Project_Comments.projectId=? AND Project_Comments.commentId=Comment.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Comment comment = new CommentService().get(rs.getInt(1));
                project.getComments().add(comment);
            }
            rs.close();
            stmt.close();


            project.setDonations(new ArrayList<>());

            stmt = conn.prepareStatement("SELECT Donation.id FROM Donation, Project_Donations "
                    + "WHERE Project_Donations.projectId=? AND Project_Donations.projectId=Donation.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Donation donation = new DonationService().get(rs.getInt(1));
                project.getDonations().add(donation);
            }

            return project;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }


    }

    /**
     * Delete project.
     *
     * @param project the project
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(Project project) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Project WHERE Project.id=" + project.getId());
            stmt.executeUpdate();
            stmt.close();

            delTables(project);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Delete project.
     *
     * @param id the project
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Project  WHERE Project.id=" + id);
            stmt.executeUpdate();
            stmt.close();

            delTables(get(id));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Add additional tables.
     *
     * @param project the project
     * @throws SQLException the SQL exception
     */
    private void addTables(Project project) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO User_projectsSubmitted VALUES(?, ?)");
            stmt.setInt(1, project.getSubmitter().getId());
            stmt.setInt(2, project.getId());
            stmt.executeUpdate();
            stmt.close();

            for (User user : project.getVolunteers()) {
                stmt = conn.prepareStatement("INSERT INTO User_projectsVolunteered VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, project.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            stmt = conn.prepareStatement("INSERT INTO User_projectsOrganized VALUES(?, ?)");
            stmt.setInt(1, project.getOrganizer().getId());
            stmt.setInt(2, project.getId());
            stmt.executeUpdate();
            stmt.close();


            for (String path : project.getImageUrls()) {
                stmt = conn.prepareStatement("INSERT INTO Project_imageUrls VALUES(?, ?)");
                stmt.setInt(1, project.getId());
                stmt.setString(2, path);
                stmt.executeUpdate();
                stmt.close();
            }

            for (Comment comment : project.getComments()) {
                stmt = conn.prepareStatement("INSERT INTO Project_Comments VALUES(?, ?)");
                stmt.setInt(1, project.getId());
                stmt.setInt(2, comment.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Donation donation : project.getDonations()) {
                stmt = conn.prepareStatement("INSERT INTO Project_Donations VALUES(?, ?)");
                stmt.setInt(1, project.getId());
                stmt.setInt(2, donation.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            //stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Delete additional tables.
     *
     * @param project the project
     * @throws SQLException the SQL exception
     */
    private void delTables(Project project) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("DELETE FROM User_projectsSubmitted  WHERE User_projectsSubmitted.projectId=" + project.getId());
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DELETE FROM User_projectsOrganized  WHERE User_projectsOrganized.projectId=" + project.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_projectsVolunteered  WHERE User_projectsVolunteered.projectId=" + project.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Project_imageUrls  WHERE Project_imageUrls.projectId=" + project.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Project_Comments  WHERE Project_Comments.projectId=" + project.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Project_Donations  WHERE Project_Donations.projectId=" + project.getId());
            stmt.executeUpdate();
            //stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }


}
