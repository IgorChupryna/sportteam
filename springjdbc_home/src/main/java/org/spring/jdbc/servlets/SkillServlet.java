package org.spring.jdbc.servlets;


import org.spring.jdbc.config.MainConfiguration;
import org.spring.jdbc.model.Skill;

import org.spring.jdbc.service.SkillServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


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


    private AbstractApplicationContext abstractApplicationContext = new AnnotationConfigApplicationContext(MainConfiguration.class);
    private SkillServiceImpl skillService = (SkillServiceImpl) abstractApplicationContext.getBean("skillDao");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Skill> skills = null;
        String errorString = null;

        skills = skillService.findAll();

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
            skill.setId((int) Long.parseLong(idParam));
            System.out.println("id: " + idParam);
        }

        skill.setName(name);

            if (skill.getName() == null || skill.getName() == "") {
                skillService.del(skill);
            } else {
                skillService.set(skill);}

            doGet(request, response);
        }

    }

