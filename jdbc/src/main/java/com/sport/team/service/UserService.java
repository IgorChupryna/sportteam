package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;
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

            stmt = conn.prepareStatement("CREATE TABLE Users(id INT PRIMARY KEY, name VARCHAR(255), "
                    + "email VARCHAR(255), phone VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Tools");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Tools(userId INT, toolId INT, "
                    + "PRIMARY KEY(userId, toolId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Skills");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Skills(userId INT, skillId INT, "
                    + "PRIMARY KEY(userId, skillId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Comments");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Comments(userId INT, commentId INT, "
                    + "PRIMARY KEY(userId, commentId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Community_Memberships");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Community_Memberships(userId INT, communityId INT, "
                    + "PRIMARY KEY(userId, communityId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Users_Community_Created");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Users_Community_Created(userId INT, communityId INT, "
                    + "PRIMARY KEY(userId, communityId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_Donation");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_Donation(userId INT, donationId INT, "
                    + "PRIMARY KEY(userId, donationId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_serviceEventsOrganized");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("CREATE TABLE User_serviceEventsOrganized(userId INT, serviceEventsOrganizedtId INT, "
                    + "PRIMARY KEY(userId, serviceEventsOrganizedtId))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_projectsSubmitted");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_projectsSubmitted(userId INT, projectId INT, "
                    + "PRIMARY KEY(userId, projectId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_projectsOrganized");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_projectsOrganized(userId INT, projectId INT, "
                    + "PRIMARY KEY(userId, projectId))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS User_projectsVolunteered");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE User_projectsVolunteered(userId INT, projectId INT, "
                    + "PRIMARY KEY(userId, projectId))");
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

            stmt = conn.prepareStatement("SELECT Tools.id, Tools.name FROM Tools, Users_Tools "
                    + "WHERE Users_Tools.userId=? AND Users_Tools.toolId=Tools.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Tool tool = new Tool();
                tool.setId(rs.getInt(1));
                tool.setName(rs.getString(2));
                user.getTools().add(tool);
            }
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Skills.id, Skills.name FROM Skills, Users_Skills "
                    + "WHERE Users_Skills.userId=? AND Users_Skills.skillId=Skills.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt(1));
                skill.setName(rs.getString(2));
                user.getSkills().add(skill);
            }


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
