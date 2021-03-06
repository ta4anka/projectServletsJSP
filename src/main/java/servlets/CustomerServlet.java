package servlets;


import model.Customer;
import model.Project;
import repository.CustomerRepository;
import repository.ProjectRepository;
import repository.hibernate.HibernateCustomerRepositoryImpl;
import repository.hibernate.HibernateProjectRepositoryImpl;

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

public class CustomerServlet extends HttpServlet {
    CustomerRepository cr = new HibernateCustomerRepositoryImpl();
    Customer customerToSave = new Customer();



    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        listCustomer(request,response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws IOException, ServletException {
        String button = request.getParameter("button");
        System.out.println("button = " + button);

        switch(button){
            case "add":
                addCustomer(request,response);
                break;
            case "edit":
                editCustomerForm(request,response);
                break;
            case "update":
                updateCustomer(request,response);
                break;
            case "delete":
                deleteCustomer(request,response);
                break;
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> CustomerList = cr.findAll();
        request.setAttribute("listOfCustomer",CustomerList);
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/customer.jsp");
        requestDispatcher.forward(request,response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfCustomer");

        ProjectRepository pr = new HibernateProjectRepositoryImpl();

        // ---Adding projects---:
        String[] arrayOfId = request.getParameterValues("names");
        Set<String> idSetString = new HashSet<>(Arrays.asList(arrayOfId));

        Set<Integer> idSetInteger = new HashSet<>();
        for (String sId : idSetString) {
            idSetInteger.add(Integer.parseInt(sId));
        }

        Set<Project> projectSet = new HashSet<>();
        for (Integer id : idSetInteger) {
            Project projectToSave = pr.getById(id);
            projectSet.add(projectToSave);
        }

        customerToSave.setName(name);
        customerToSave.setProjects(projectSet);
        cr.save(customerToSave);
        response.sendRedirect("customer");
    }

    private void editCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher  = request.getRequestDispatcher("view/editCustomerForm.jsp");
        requestDispatcher.forward(request,response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idOfUpdatedCustomer"));
        String nameOfUpdatedCustomer = request.getParameter("nameOfUpdatedCustomer");
        customerToSave.setId(id);
        customerToSave.setName(nameOfUpdatedCustomer);
        cr.update(customerToSave);
        response.sendRedirect("customer");
    }


    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String strId = request.getParameter("customerId");
        int id = Integer.parseInt(strId);
        cr.delete(id);
        response.sendRedirect("customer");
    }
}
