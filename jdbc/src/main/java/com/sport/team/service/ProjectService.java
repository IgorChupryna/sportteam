package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Project;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            stmt = conn.prepareStatement("CREATE TABLE Project(id INT PRIMARY KEY,  address1 VARCHAR(255), address2 VARCHAR(255),city VARCHAR(255), dateAdded VARCHAR(255), " +
                    "description VARCHAR(255), email VARCHAR(255),firstname  VARCHAR(255), lastname VARCHAR(255), phone VARCHAR(255), state VARCHAR(255), zip VARCHAR(255),title VARCHAR(255))");

            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_Donations");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_Donations(projectId INT, donationId INT, "
                    + "PRIMARY KEY(projectId, donationId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_Comments");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_Comments(projectId INT, commentId INT, "
                    + "PRIMARY KEY(projectId, commentId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_imageUrls");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_imageUrls(projectId INT, imageId INT, path VARCHAR(255) ,"
                    + "PRIMARY KEY(projectId, imageId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Project_serviceEvent");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Project_serviceEvent(projectId INT, serviceEventId INT, path VARCHAR(255) ,"
                    + "PRIMARY KEY(projectId, serviceEventId))");
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


    @Override
    public void add(Project project) throws SQLException {

    }

    @Override
    public void set(Project project) throws SQLException {

    }

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

    @Override
    public void del(Project project) throws SQLException {

    }

    @Override
    public void del(int id) throws SQLException {

    }
}
