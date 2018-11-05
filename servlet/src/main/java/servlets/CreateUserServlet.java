package servlets;


import crud.ToolService;
import entity.Tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = { "/createUser" })
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tool> tools=null;
        String errorString = null;
        Tool tool=null;
        try {
            tool = new ToolService().get(1L);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("tool", tool);
        /**
        try {
           tools = new ToolService().getAll();
        } catch (SQLException e) {
            errorString=e.getMessage();
        }**/

        request.setAttribute("errorString", errorString);
       // request.setAttribute("toolList", tools);

        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/create_user.jsp");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/first.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
