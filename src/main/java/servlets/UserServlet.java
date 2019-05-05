package servlets;

import controller.SkillController;
import controller.UserController;
import model.Skill;
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

public class UserServlet extends HttpServlet {
    private UserController uc = new UserController();
    private User userToSave = new User();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listUser(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch(button){
            case "add":
                addUser(request,response);
                break;
            case "edit":
                editUserForm(request,response);
                break;
            case "update":
                updateUser(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
        }

    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = uc.findAll();
        request.setAttribute("listOfUser",userList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/user.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SkillController sc = new SkillController();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");
            // ---Adding skills---:
        String[] arrayOfId = request.getParameterValues("names");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));

        Set<Integer> idSetInteger = new HashSet<>();
        for (String sId : idSetString) {
            idSetInteger.add(Integer.parseInt(sId));
        }

        Set<Skill> skillSet = new HashSet<>();
        for (Integer id : idSetInteger) {
            Skill skillToSave = sc.getById(id);
            skillSet.add(skillToSave);
        }
            // ------------------------;

        userToSave.setFirstName(firstName);
        userToSave.setLastName(lastName);
        userToSave.setSpecialty(specialty);
        userToSave.setSkills(skillSet);
        uc.save(userToSave);
        response.sendRedirect("user");
    }


    private void editUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/editUserForm.jsp");
        requestDispatcher.forward(request,response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedUser"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");
        userToSave.setId(id);
        userToSave.setFirstName(firstName);
        userToSave.setLastName(lastName);
        userToSave.setSpecialty(specialty);
        uc.update(userToSave);
        response.sendRedirect("user");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("userId");
        int id = Integer.parseInt(strId);
        uc.delete(id);
        response.sendRedirect("user");
    }

}
