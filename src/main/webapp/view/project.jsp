<%@ page import="model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %>
<%@ page import="repository.TeamRepository" %>
<%@ page import="repository.hibernate.HibernateTeamRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  Team: maya
  Date: 06.05.19
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Projects</title>
</head>
<body>

<div class="w3-panel w3-blue">
    <h1 class="w3-text-orange" style="text-shadow:1px 1px 0 #444" align="center" ><b>Project</b></h1>
</div>

<div align="center">
    <form method ="post">
        <%--the value of atribute 'name' is used in getParameter() method--%>
        <label>name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="nameOfProject"><br />
        </label>
        <label>budget:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="budget"><br />
        </label>

        <label>Teams for project:
            <table>
                <% TeamRepository tr = new HibernateTeamRepositoryImpl();
                    List<Team> teams = tr.findAll();
                    for (Team t : teams){ %>
                <tr>
                    <td><input type="checkbox" name="names" value ="<%=t.getId()%>"><%=t.getName()%></td>
                </tr>
                <%}%>
            </table>
        </label>

        <button class="w3-btn w3-grey w3-round-medium" type="submit" name="button" value="add">add new Project</button>
    </form>

    <table  border="1" cellpadding="5">
        <caption><h2>List of all Project</h2></caption>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Name</th>
            <th>Budget</th>
            <th>Actions</th>
        </tr>
        <% List<Project> Projects = (List<Project>) request.getAttribute("listOfProject");
            for (Project p : Projects){ %>
        <tr>
            <td> <%=p.getId()%></td>
            <td><%=p.getName()%></td>
            <td><%=p.getBudget()%></td>
            
            <td>
                <form action="project" method="post">
                    <input  type="hidden" name="projectId" value="<%=p.getId()%>"/>
                    <%--the value of atribute 'value' is used in doPost() method, in the switch statement --%>
                    <button class="w3-btn w3-blue w3-round-medium" type="submit" name="button" value="edit">edit</button>
                    <button class="w3-btn w3-red w3-round-medium" type="submit" name="button" value="delete">delete</button>
                </form>
            </td>
        </tr>
        <% } %>

    </table>
</div>
</body>

</html>
