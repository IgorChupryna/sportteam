package com.sport.team;

import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainJdbc {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        try {

            initDb();

            Tool tool = new Tool();
            tool.setId(1);
            tool.setName("Hammer");
            insertTool(tool);
            List<Tool> tools = new ArrayList();
            tools.add(tool);

            Skill skill = new Skill();
            skill.setId(1);
            skill.setName("Hammering Things");
            insertSkill(skill);
            List<Skill> skills = new ArrayList();
            skills.add(skill);

            User user = new User();
            user.setId(1);
            user.setName("Brett Meyer");
            user.setEmail("foo@foo.com");
            user.setPhone("123-456-7890");
            user.setTools(tools);
            user.setSkills(skills);

            insertUser(user);

            user = getUser(1);
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Inits the db.
     *
     * @throws SQLException the SQL exception
     */
    private static void initDb() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connection();

            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("CREATE TABLE Users(id INT PRIMARY KEY, name VARCHAR(255), "
                    + "email VARCHAR(255), phone VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Tools(id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Skills(id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Users_Tools(userId INT, toolId INT, "
                    + "PRIMARY KEY(userId, toolId))");
            stmt.executeUpdate();
            stmt.close();
            stmt = conn.prepareStatement("CREATE TABLE Users_Skills(userId INT, skillId INT, "
                    + "PRIMARY KEY(userId, skillId))");
            stmt.executeUpdate();
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

}
