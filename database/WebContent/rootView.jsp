<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Root Page</title>
</head>
<body>

<div align="center">

    <form action="initialize">
        <input type="submit" value="Initialize the Database" />
    </form>
    <a href="login.jsp" target="_self">Logout</a><br><br>

    <h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.role}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
