
<%@ page import="model.Customer" %>
<%@ page import="repository.CustomerRepository" %>
<%@ page import="repository.hibernate.HibernateCustomerRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  User: maya
  Date: 06.05.19
  Time: 14:02
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
    <form action = "customer" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit Customer</h3></caption>
            <% CustomerRepository cr =new HibernateCustomerRepositoryImpl();
                int idForEdit = Integer.parseInt(request.getParameter("customerId"));
                Customer customerForEdit = cr.getById(idForEdit);%>
            <tr>
                <th>id of customer:</th>
                <td>
                    <input type="text" name="idOfUpdatedCustomer" size="30" value = "<%= customerForEdit.getId()%>" readonly/>
                </td>
            </tr>
            <tr>
                <th>name of Customer: </th>
                <td>
                    <input type="text" name="nameOfUpdatedCustomer" size="30" value = "<%= customerForEdit.getName()%>"/>
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
