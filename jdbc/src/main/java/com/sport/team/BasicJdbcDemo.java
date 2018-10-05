package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class BasicJdbcDemo.
 *
 * @author aleksey
 */
public class BasicJdbcDemo {


    /**
     * Insert user.
     *
     * @param user the user
     * @throws SQLException the SQL exception
     */
    private static void insertUser(User user) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = connection();

            stmt = conn.prepareStatement("INSERT INTO Users VALUES(?, ?, ?, ?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.executeUpdate();
            stmt.close();

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

            if(conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Insert tool.
     *
     * @param tool the tool
     * @throws SQLException the SQL exception
     */
    private static void insertTool(Tool tool) throws SQLException {

        PreparedStatement stmt = null;

        try(Connection conn = connection()){

            stmt = conn.prepareStatement("INSERT INTO Tools VALUES(?, ?)");
            stmt.setInt(1, tool.getId());
            stmt.setString(2, tool.getName());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Insert skill.
     *
     * @param skill the skill
     * @throws SQLException the SQL exception
     */
    private static void insertSkill(Skill skill) throws SQLException {

        PreparedStatement stmt = null;

        try(Connection conn = connection()){

            stmt = conn.prepareStatement("INSERT INTO Skills VALUES(?, ?)");
            stmt.setInt(1, skill.getId());
            stmt.setString(2, skill.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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
    private static User getUser(int id) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = connection()){

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
     * Connection.
     *
     * @return the connection
     * @throws SQLException           the SQL exception
     * @throws ClassNotFoundException the class not found exception
     */
    private static Connection connection() throws SQLException, ClassNotFoundException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://10.91.63.108:3306/userdb");
        dataSource.setUser("monty");
        dataSource.setPassword("totoadmin");

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), "totoadmin");
        conn.setAutoCommit(true);
        return conn;
    }


}
