
<%@ page import="model.Project" %>
<%@ page import="repository.ProjectRepository" %>
<%@ page import="repository.hibernate.HibernateProjectRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action = "project" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit Project</h3></caption>
            <% ProjectRepository pr =new HibernateProjectRepositoryImpl();
                int idForEdit = Integer.parseInt(request.getParameter("projectId"));
                Project projectForEdit = pr.getById(idForEdit);%>
            <tr>
                <th>id of project:</th>
                <td>
                    <input type="text" name="idOfUpdatedProject" size="30" value = "<%= projectForEdit.getId()%>" readonly/>
                </td>
            </tr>
            <tr>
                <th>name of Project: </th>
                <td>
                    <input type="text" name="nameOfUpdatedProject" size="30" value = "<%= projectForEdit.getName()%>"/>
                </td>
            </tr>
            <tr>
                <th>budget of Project: </th>
                <td>
                    <input type="text" name="budget" size="30" value = "<%= projectForEdit.getBudget()%>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button class="w3-btn w3-deep-purple w3-round-medium" type="submit" name="button" value="update">update</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
