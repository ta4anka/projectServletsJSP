<%@ page import="model.Team" %>
<%@ page import="repository.TeamRepository" %>
<%@ page import="repository.hibernate.HibernateTeamRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 12:05
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
    <form action = "team" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit Team</h3></caption>
            <% TeamRepository tr =new HibernateTeamRepositoryImpl();
                int idForEdit = Integer.parseInt(request.getParameter("teamId"));
                Team teamForEdit = tr.getById(idForEdit);%>
            <tr>
                <th>id of team:</th>
                <td>
                    <input type="text" name="idOfUpdatedTeam" size="30" value = "<%= teamForEdit.getId()%>" readonly/>
                </td>
            </tr>
            <tr>
                <th>name of Team: </th>
                <td>
                    <input type="text" name="nameOfUpdatedTeam" size="30" value = "<%= teamForEdit.getName()%>"/>
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
