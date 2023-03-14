<%--
  Created by IntelliJ IDEA.
  User: vipqk
  Date: 14/03/2023
  Time: 00:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="5">
    <caption><h2>List of Employees</h2></caption>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="food" items="${foods}">
        <tr>
            <td><c:out value="${food.id}"/></td>
            <td><c:out value="${food.name}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
