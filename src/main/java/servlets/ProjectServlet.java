package servlets;


import model.Project;
import model.Team;
import repository.ProjectRepository;
import repository.TeamRepository;
import repository.hibernate.HibernateProjectRepositoryImpl;
import repository.hibernate.HibernateTeamRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectServlet extends HttpServlet {
    ProjectRepository pr = new HibernateProjectRepositoryImpl();
    Project projectToSave = new Project();



    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listProject(request,response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch(button){
            case "add":
                addProject(request,response);
                break;
            case "edit":
                editProjectForm(request,response);
                break;
            case "update":
                updateProject(request,response);
                break;
            case "delete":
                deleteProject(request,response);
                break;
        }
    }

    private void listProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> ProjectList = pr.findAll();
        request.setAttribute("listOfProject",ProjectList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/project.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfProject");
        BigDecimal budget = new BigDecimal(request.getParameter("budget"));

        TeamRepository tr = new HibernateTeamRepositoryImpl();

        // ---Adding teams---:
        String[] arrayOfId = request.getParameterValues("names");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));

        Set<Integer> idSetInteger = new HashSet<>();
        for (String sId : idSetString) {
            idSetInteger.add(Integer.parseInt(sId));
        }

        Set<Team> teamSet = new HashSet<>();
        for (Integer id : idSetInteger) {
            Team teamToSave = tr.getById(id);
            teamSet.add(teamToSave);
        }


        projectToSave.setName(name);
        projectToSave.setBudget(budget);
        projectToSave.setTeams(teamSet);
        pr.save(projectToSave);
        response.sendRedirect("project");
    }

    private void editProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/editProjectForm.jsp");
        requestDispatcher.forward(request,response);
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedProject"));
        String nameOfUpdatedProject = request.getParameter("nameOfUpdatedProject");
        BigDecimal budget = new BigDecimal(request.getParameter("budget"));

        projectToSave.setId(id);
        projectToSave.setName(nameOfUpdatedProject);
        projectToSave.setBudget(budget);
        pr.update(projectToSave);
        response.sendRedirect("project");
    }


    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("projectId");
        int id = Integer.parseInt(strId);
        pr.delete(id);
        response.sendRedirect("project");
    }
}
