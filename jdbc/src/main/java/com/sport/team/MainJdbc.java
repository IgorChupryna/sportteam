package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;
import com.sport.team.service.SkillService;
import com.sport.team.service.ToolService;
import com.sport.team.service.UserService;

import java.sql.*;
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
            MainService service=null;

            initDb();

            service=new ToolService();
            Tool tool = new Tool();
            tool.setId(5);
            tool.setName("Hammer3");
            service.add(tool);


            List<Tool> tools = new ArrayList();
            tools.add(tool);

            service=new SkillService();
            Skill skill = new Skill();
            skill.setId(5);
            skill.setName("Hammering Things3");
            service.add(skill);


            List<Skill> skills = new ArrayList();
            skills.add(skill);

            service=new UserService();
            User user = new User();
            user.setId(1);
            user.setName("Brett Meyer");
            user.setEmail("foo@foo.com");
            user.setPhone("123-456-7890");
            user.setTools(tools);
            user.setSkills(skills);

            service.add(user);

            user = (User) service.get(1);
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Connection.
     *
     * @return the connection
     * @throws SQLException           the SQL exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static Connection connection() throws SQLException, ClassNotFoundException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://10.91.63.108:3306/userdb");
        dataSource.setUser("monty");
        dataSource.setPassword("totoadmin");

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), "totoadmin");
        conn.setAutoCommit(true);
        return conn;
    }



    /**
     * Inits the db.
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

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Tools");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Tools(id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Skills");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Skills(id INT PRIMARY KEY, name VARCHAR(255))");
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

            //stmt.close();



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
