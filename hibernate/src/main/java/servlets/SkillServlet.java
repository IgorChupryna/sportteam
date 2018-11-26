package servlets;

import crud.SkillService;
import entity.Skill;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = {"/skill"})
public class SkillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SkillService skillService = new SkillService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Skill> skills = null;
        String errorString = null;

        try {
            skills = new SkillService().getAll();
        } catch (SQLException e) {
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("skillList", skills);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/skills.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String idParam = request.getParameter("id");
        Skill skill = new Skill();


        if (idParam != null && !idParam.isEmpty()) {
            skill.setId(Long.parseLong(idParam));
            System.out.println("id: " + idParam);
        }

        skill.setName(name);

        try {
            if (skill.getName() == null || skill.getName() == "") {
                skillService.del(skill);
            } else {
                skillService.set(skill);}
            } catch(SQLException e){
                e.printStackTrace();
            }

            doGet(request, response);
        }



    }

