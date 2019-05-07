<%@ page import="model.User" %>
<%@ page import="repository.UserRepository" %>
<%@ page import="repository.hibernate.HibernateUserRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>User edit form</title>
</head>
<body>
<div align="center">
    <form action = "user" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit user</h3></caption>
            <% UserRepository sr =new HibernateUserRepositoryImpl();
                int idForEdit = Integer.parseInt(request.getParameter("userId"));
                User userForEdit = sr.getById(idForEdit);%>
            <tr>
                <th>Id of user:</th>
                <td>
                    <input type="text" name="idOfUpdatedUser" size="30" value = "<%= userForEdit.getId()%>" readonly/>
                </td>
            </tr>

            <tr>
                <th>First name: </th>
                <td>
                    <input type="text" name="firstName" size="30" value = "<%= userForEdit.getFirstName()%>"/>
                </td>
            </tr>
            <tr>
                <th>Last name: </th>
                <td>
                    <input type="text" name="lastName" size="30" value = "<%= userForEdit.getLastName()%>"/>
                </td>
            </tr>
            <tr>
                <th>Specialty: </th>
                <td>
                    <input type="text" name="specialty" size="30" value = "<%= userForEdit.getSpecialty()%>"/>
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
