<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h1>List of Users</h1>
    <table border="1" cellpadding="6">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Password</th>
            <th>Email</th>
            <th>Address</th>
            <th>Zip Code</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${get_user}">
            <tr style="text-align:center">
                <td>${user.FirstName}</td>
                <td>${user.LastName}</td>
                <td>${user.Password}</td>
                <td>${user.Email}</td>
                <td>${user.Address}</td>
                <td>${user.ZipCode}</td>
                <td>${user.Role}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
