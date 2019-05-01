package servlets;

import java.io.IOException;

public class SkillServlet extends javax.servlet.http.HttpServlet {
    private static final String STRING_PATH = "WEB-INF/view/skill.jsp";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher(STRING_PATH).forward(request, response);

    }
}
