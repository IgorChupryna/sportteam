package org.spring.jdbc.servlets;

import org.spring.jdbc.config.MainConfiguration;
import org.spring.jdbc.service.MainService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/initdb" })
public class MainServlet extends HttpServlet {
    private AbstractApplicationContext abstractApplicationContext = new AnnotationConfigApplicationContext(MainConfiguration.class);
    private MainService mainService = (MainService) abstractApplicationContext.getBean("mainDao");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        mainService.initDB();
        super.doGet(req, resp);
    }

}
