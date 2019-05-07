package servlets;


import model.Skill;
import repository.SkillRepository;
import repository.hibernate.HibernateSkillRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SkillServlet extends javax.servlet.http.HttpServlet {
    private SkillRepository sr = new HibernateSkillRepositoryImpl();
    private Skill skillToSave = new Skill();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listSkill(request,response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button); // for debugging TODO --> DELETE

        switch(button){
            case "add":
                addSkill(request,response);
                break;
            case "edit":
                editSkillForm(request,response);
                break;
            case "update":
                updateSkill(request,response);
                break;
            case "delete":
                deleteSkill(request,response);
                break;
        }
    }

    private void listSkill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Skill> skillList = sr.findAll();
        request.setAttribute("listOfSkill",skillList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/skill.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addSkill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfSkill");
        skillToSave.setName(name);
        sr.save(skillToSave);
        response.sendRedirect("skill");
    }

    private void editSkillForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/editSkillForm.jsp");
        requestDispatcher.forward(request,response);
    }

    private void updateSkill(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedSkill"));
        String nameOfUpdatedSkill = request.getParameter("nameOfUpdatedSkill");
        skillToSave.setId(id);
        skillToSave.setName(nameOfUpdatedSkill);
        sr.update(skillToSave);
        response.sendRedirect("skill");
    }


    private void deleteSkill(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("skillId");
        System.out.println("ID of deleting skill =   " + strId); // for debugging TODO --> DELETE
        int id = Integer.parseInt(strId);
        sr.delete(id);
        response.sendRedirect("skill");
    }

}