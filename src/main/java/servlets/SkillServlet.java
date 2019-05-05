package servlets;

import controller.SkillController;
import model.Skill;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SkillServlet extends javax.servlet.http.HttpServlet {
    SkillController sc = new SkillController();

    private static final String STRING_PATH = "view/skill.jsp";

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        List<Skill> skillList = sc.findAll();
        request.setAttribute("listOfSkill",skillList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/skill.jsp");
        requestDispatcher.forward(request,response);
    }
    //--------------------------------------------------------------------------------------------------------
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws  IOException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch(button){
            case "delete":
                deleteSkill(request,response);
                break;
            case "add":
                addSkill(request,response);
                break;
            case "edit":
                editSkill(request,response);
                break;

        }
    }


    private void addSkill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfSkill");
        System.out.println("NAME =  " + name); // for debugging TODO --> DELETE
        Skill skill = new Skill();
        skill.setName(name);
        sc.save(skill);
        response.sendRedirect("skill");
    }

    private void deleteSkill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strId = request.getParameter("skillId");
        System.out.println("ID =   " + strId); // for debugging TODO --> DELETE
        int id = Integer.parseInt(strId);
        sc.delete(id);
        response.sendRedirect("skill");
    }

    private void editSkill(HttpServletRequest request, HttpServletResponse response) {
    }



    private void listSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Skill> skillList = sc.findAll();
        request.setAttribute("listOfSkill",skillList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/skill.jsp");
        requestDispatcher.forward(request,response);


    }

    private void updateSkill(HttpServletRequest request, HttpServletResponse response) {
    }




}