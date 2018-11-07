package com.sport.team;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;
import com.sport.team.service.*;

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

            //service.add(user);

            //user = (User) service.get(1);
            //System.out.println(user.toString());
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

        ToolService.initDb();
        SkillService.initDb();
        CommentService.initDb();
        CommunityService.initDb();
        DonationService.initDb();
        ServiceEventService.initDb();
        UserService.initDb();
        ProjectService.initDb();

    }

}
