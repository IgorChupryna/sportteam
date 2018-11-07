package servlets;


import crud.ToolService;
import crud.UserService;
import entity.Tool;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = null;
        String errorString = null;

        try {
            users = new UserService().getAll();
        } catch (SQLException e) {
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("userList", users);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/users.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
