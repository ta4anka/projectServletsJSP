package servlets;


import controller.TeamController;
import controller.UserController;

import model.Team;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamServlet extends HttpServlet {
    TeamController tc = new TeamController();
    Team teamToSave = new Team();



    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listTeam(request,response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch(button){
            case "add":
                addTeam(request,response);
                break;
            case "edit":
                editTeamForm(request,response);
                break;
            case "update":
                updateTeam(request,response);
                break;
            case "delete":
                deleteTeam(request,response);
                break;
        }
    }

    private void listTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> TeamList = tc.findAll();
        request.setAttribute("listOfTeam",TeamList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/team.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfTeam");

        UserController sc = new UserController();

        // ---Adding users---:
        String[] arrayOfId = request.getParameterValues("names");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));

        Set<Integer> idSetInteger = new HashSet<>();
        for (String sId : idSetString) {
            idSetInteger.add(Integer.parseInt(sId));
        }

        Set<User> userSet = new HashSet<>();
        for (Integer id : idSetInteger) {
            User userToSave = sc.getById(id);
            userSet.add(userToSave);
        }

        teamToSave.setName(name);
        teamToSave.setUsers(userSet);
        tc.save(teamToSave);
        response.sendRedirect("team");
    }

    private void editTeamForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/editTeamForm.jsp");
        requestDispatcher.forward(request,response);
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedTeam"));
        String nameOfUpdatedTeam = request.getParameter("nameOfUpdatedTeam");
        teamToSave.setId(id);
        teamToSave.setName(nameOfUpdatedTeam);
        tc.update(teamToSave);
        response.sendRedirect("team");
    }


    private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("teamId");
        int id = Integer.parseInt(strId);
        tc.delete(id);
        response.sendRedirect("team");
    }
}
