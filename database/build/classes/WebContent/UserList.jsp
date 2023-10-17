<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h2>List of Users</h2>
    <div align="center">
        <table border="1" cellpadding="5">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.role}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
