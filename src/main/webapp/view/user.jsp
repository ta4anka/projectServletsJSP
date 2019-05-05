<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Skill" %>
<%@ page import="controller.SkillController" %><%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>User</title>
</head>
<body>
<div class="w3-panel w3-blue">
    <h1 class="w3-text-orange" style="text-shadow:1px 1px 0 #444" align="center" ><b>User</b></h1>
</div>

<div align="center">
    <form method ="post">
        <%--the value of atribute 'name' is used in getParameter() method--%>
        <label>first name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="firstName"><br />
        </label>
        <label>last name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="lastName"><br />
        </label>
        <label>specialty:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="specialty"><br />
        </label>

            <label>Skills:
                <table>
                <% SkillController sc = new SkillController();
                List<Skill> skills = sc.findAll();
                for (Skill s : skills){ %>
                    <tr>
                        <td><input type="checkbox" name="names" value ="<%=s.getId()%>"><%=s.getName()%></td>
                    </tr>
                    <%}%>
                </table>
            </label>


        <button class="w3-btn w3-grey w3-round-medium" type="submit" name="button" value="add">add new user</button>
    </form>

    <table  border="1" cellpadding="5">
        <caption><h2>List of all users</h2></caption>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Specialty</th>
            <th>Actions</th>
        </tr>
        <% List<User> users = (List<User>) request.getAttribute("listOfUser");
            for (User u : users){ %>
        <tr>
            <td> <%=u.getId()%></td>
            <td><%=u.getFirstName()%></td>
            <td><%=u.getLastName()%></td>
            <td><%=u.getSpecialty()%></td>
            <td>
                <form action="user" method="post">
                    <input  type="hidden" name="userId" value="<%=u.getId()%>"/>
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
