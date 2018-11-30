package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.*;
import com.sport.team.interfaces.MainService;

import java.sql.*;
import java.util.ArrayList;


/**
 * The Class BasicJdbcDemo.
 *
 * @author aleksey
 */
public class UserService implements MainService<User> {

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

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users(id INT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), phone VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Tools");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Tools(userId INT, toolId INT, PRIMARY KEY(userId, toolId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Skills");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Skills(userId INT, skillId INT, PRIMARY KEY(userId, skillId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Comments");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Comments(userId INT, commentId INT, PRIMARY KEY(userId, commentId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Community_Memberships");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Community_Memberships(userId INT, communityId INT, PRIMARY KEY(userId, communityId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Community_Created");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Community_Created(userId INT, communityId INT, PRIMARY KEY(userId, communityId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_Donation");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_Donation(userId INT, donationId INT, PRIMARY KEY(userId, donationId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_Service_EO");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("CREATE TABLE User_Service_EO(userId INT, serviceEOId INT, PRIMARY KEY(userId, serviceEOId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_projectsSubmitted");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_projectsSubmitted(userId INT, projectId INT, PRIMARY KEY(userId, projectId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_projectsOrganized");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_projectsOrganized(userId INT, projectId INT, PRIMARY KEY(userId, projectId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_projectsVolunteered");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_projectsVolunteered(userId INT, projectId INT, PRIMARY KEY(userId, projectId))");
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
     * Insert user.
     *
     * @param user the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(User user) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Users VALUES(?, ?, ?, ?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.executeUpdate();
            stmt.close();

            addTables(user);
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
     * Update user.
     *
     * @param user the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(User user) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();
            stmt = conn.prepareStatement("UPDATE Users SET Users.name=?,Users.email=?,Users.phone=? WHERE Users.id=?");

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setInt(4, user.getId());

            stmt.executeUpdate();
            stmt.close();

            delTables(user);
            addTables(user);
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
     * Add additional tables.
     *
     * @param user the user
     * @throws SQLException the SQL exception
     */
    private void addTables(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            for (Tool tool : user.getTools()) {
                stmt = conn.prepareStatement("INSERT INTO Users_Tools VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, tool.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Skill skill : user.getSkills()) {
                stmt = conn.prepareStatement("INSERT INTO Users_Skills VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, skill.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Comment comment : user.getComments()) {
                stmt = conn.prepareStatement("INSERT INTO Users_Comments VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, comment.getId());
                stmt.executeUpdate();
                stmt.close();
            }
            for (Community community : user.getCommunitiesCreated()) {
                stmt = conn.prepareStatement("INSERT INTO Users_Community_Created VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, community.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Community community : user.getCommunityMemberships()) {
                stmt = conn.prepareStatement("INSERT INTO Users_Community_Memberships VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, community.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Donation donation : user.getDonations()) {
                stmt = conn.prepareStatement("INSERT INTO User_Donation VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, donation.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Project project : user.getProjectsOrganized()) {
                stmt = conn.prepareStatement("INSERT INTO User_projectsOrganized VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, project.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Project project : user.getProjectsSubmitted()) {
                stmt = conn.prepareStatement("INSERT INTO User_projectsSubmitted VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, project.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (Project project : user.getProjectsVolunteered()) {
                stmt = conn.prepareStatement("INSERT INTO User_projectsVolunteered VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, project.getId());
                stmt.executeUpdate();
                stmt.close();
            }

            for (ServiceEvent serviceEvent : user.getServiceEventsOrganized()) {
                stmt = conn.prepareStatement("INSERT INTO User_Service_EO VALUES(?, ?)");
                stmt.setInt(1, user.getId());
                stmt.setInt(2, serviceEvent.getId());
                stmt.executeUpdate();
                stmt.close();
            }


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
     * @param user the user
     * @throws SQLException the SQL exception
     */
    private void delTables(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("DELETE FROM Users_Tools  WHERE Users_Tools.toolId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Users_Skills  WHERE Users_Skills.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Users_Community_Memberships  WHERE Users_Community_Memberships.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Users_Community_Created  WHERE Users_Community_Created.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Users_Comments  WHERE Users_Comments.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_Service_EO  WHERE User_Service_EO.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_projectsVolunteered  WHERE User_projectsVolunteered.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_projectsSubmitted  WHERE User_projectsSubmitted.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_projectsOrganized  WHERE User_projectsOrganized.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_Donation  WHERE User_Donation.userId=" + user.getId());
            stmt.executeUpdate();
            stmt.close();




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
     * Gets the user.
     *
     * @param id the id
     * @return the user
     * @throws SQLException the SQL exception
     */
    @Override
    public User get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Users.id, Users.name, Users.email, Users.phone FROM Users WHERE Users.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setPhone(rs.getString(4));

            rs.close();
            stmt.close();

            user.setTools(new ArrayList<Tool>());
            user.setSkills(new ArrayList<Skill>());

            stmt = conn.prepareStatement("SELECT Tools.id FROM Tools, Users_Tools "
                    + "WHERE Users_Tools.userId=? AND Users_Tools.toolId=Tools.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Tool tool = new ToolService().get(rs.getInt(1));
                user.getTools().add(tool);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Skills.id FROM Skills, Users_Skills "
                    + "WHERE Users_Skills.userId=? AND Users_Skills.skillId=Skills.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Skill skill = new SkillService().get(rs.getInt(1));
                user.getSkills().add(skill);
            }

            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Comment.id FROM Comment, Users_Comments "
                    + "WHERE Users_Comments.userId=? AND Users_Comments.commentId=Comment.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Comment comment = new CommentService().get(rs.getInt(1));
                user.getComments().add(comment);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Community.id FROM Community, Users_Community_Created "
                    + "WHERE Users_Community_Created.userId=? AND Users_Community_Created.communityId=Community.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Community community = new CommunityService().get(rs.getInt(1));
                user.getCommunitiesCreated().add(community);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Community.id FROM Community, Users_Community_Memberships "
                    + "WHERE Users_Community_Memberships.userId=? AND Users_Community_Memberships.communityId=Community.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Community community = new CommunityService().get(rs.getInt(1));
                user.getCommunityMemberships().add(community);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Donation.id FROM Donation, User_Donation "
                    + "WHERE User_Donation.userId=? AND User_Donation.donationId=Donation.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Donation donation = new DonationService().get(rs.getInt(1));
                user.getDonations().add(donation);
            }
            rs.close();
            stmt.close();


            stmt = conn.prepareStatement("SELECT Service_EO.id FROM Service_EO, User_Service_EO "
                    + "WHERE User_Service_EO.userId=? AND User_Service_EO.serviceEOId=Service_EO.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ServiceEvent serviceEvent = new ServiceEventService().get(rs.getInt(1));
                user.getServiceEventsOrganized().add(serviceEvent);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Project.id FROM Project, User_projectsOrganized "
                    + "WHERE User_projectsOrganized.userId=? AND User_projectsOrganized.projectId=Project.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Project project = new ProjectService().get(rs.getInt(1));
                user.getProjectsOrganized().add(project);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Project.id FROM Project, User_projectsSubmitted "
                    + "WHERE User_projectsSubmitted.userId=? AND User_projectsSubmitted.projectId=Project.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Project project = new ProjectService().get(rs.getInt(1));
                user.getProjectsSubmitted().add(project);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Project.id FROM Project, User_projectsVolunteered "
                    + "WHERE User_projectsVolunteered.userId=? AND User_projectsVolunteered.projectId=Project.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Project project = new ProjectService().get(rs.getInt(1));
                user.getProjectsVolunteered().add(project);
            }
            rs.close();
            stmt.close();



            return user;
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
     * Delete user.
     *
     * @param user the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(User user) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Users  WHERE Users.id=" + user.getId());
            stmt.executeUpdate();
            stmt.close();

            delTables(user);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    /**
     * Delete user.
     *
     * @param id the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Users  WHERE Users.id=" + id);
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
}
