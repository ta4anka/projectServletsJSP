<%@ page import="model.Skill" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 04.05.19
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Skills</title>
</head>
<body>

<div class="w3-panel w3-blue">
    <h1 class="w3-text-orange" style="text-shadow:1px 1px 0 #444" align="center" ><b>Skill</b></h1>
</div>

<div align="center">
    <form method ="post">
        <%--the value of atribute 'name' is used in getParameter() method--%>
        <label>name:
        <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="nameOfSkill"><br />
        </label>
            <button class="w3-btn w3-grey w3-round-medium" type="submit" name="button" value="add">add new skill</button>
    </form>

    <table  border="1" cellpadding="5">
        <caption><h2>List of all skill</h2></caption>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <% List<Skill> skills = (List<Skill>) request.getAttribute("listOfSkill");
            for (Skill s : skills){ %>
        <tr>
            <td> <%=s.getId()%></td>
            <td><%=s.getName()%></td>
            <td>
        <form action="skill" method="post">
            <input  type="hidden" name="skillId" value="<%=s.getId()%>"/>
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